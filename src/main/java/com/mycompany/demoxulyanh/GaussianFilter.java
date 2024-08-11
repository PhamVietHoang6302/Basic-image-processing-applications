/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author PhamVietHoang
 */
public class GaussianFilter {

    public static BufferedImage gaussianBlur(BufferedImage img, double sigma) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage resultImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Kernel Gaussian
        double[][] kernel = generateGaussianKernel(sigma);

        // Áp dụng lọc Gaussian cho từng pixel trong ảnh
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double[] sum = {0.0, 0.0, 0.0};

                // Tính tổng trọng số của các pixel lân cận
                for (int j = -2; j <= 2; j++) {
                    for (int i = -2; i <= 2; i++) {
                        int pixelX = Math.min(Math.max(x + i, 0), width - 1);
                        int pixelY = Math.min(Math.max(y + j, 0), height - 1);
                        Color color = new Color(img.getRGB(pixelX, pixelY));
                        double weight = kernel[j + 2][i + 2];
                        sum[0] += color.getRed() * weight;
                        sum[1] += color.getGreen() * weight;
                        sum[2] += color.getBlue() * weight;
                    }
                }

                // Gán giá trị pixel mới vào ảnh đích
                int newRed = (int) Math.round(sum[0]);
                int newGreen = (int) Math.round(sum[1]);
                int newBlue = (int) Math.round(sum[2]);
                int newPixel = (newRed << 16) | (newGreen << 8) | newBlue;
                resultImg.setRGB(x, y, newPixel);
            }
        }
        
        return resultImg;
    }

// Hàm tạo kernel Gaussian
    private static double[][] generateGaussianKernel(double sigma) {
        int size = 5;
        double[][] kernel = new double[size][size];
        double sum = 0;

        for (int j = -2; j <= 2; j++) {
            for (int i = -2; i <= 2; i++) {
                double value = Math.exp(-(i * i + j * j) / (2 * sigma * sigma)) / (2 * Math.PI * sigma * sigma);
                kernel[j + 2][i + 2] = value;
                sum += value;
            }
        }

        // Chuẩn hóa kernel
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                kernel[j][i] /= sum;
            }
        }

        System.out.println(sum);

        return kernel;
    }

}
