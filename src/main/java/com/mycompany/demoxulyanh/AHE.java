/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.awt.image.BufferedImage;

/**
 *
 * @author PhamVietHoang
 */
public class AHE {

   
       private static final int CLAHE_BUFFER_SIZE = 256;
    private static final int CLAHE_TILES_X = 8;
    private static final int CLAHE_TILES_Y = 8;

    public static BufferedImage aheFilter(BufferedImage img, int windowSize, int clipLimit) {
        int width = img.getWidth();
        int height = img.getHeight();
        Pixel[][] data = DataImage.getImageData(img);
        // Tạo bức ảnh kết quả
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Duyệt qua từng pixel trong ảnh
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Tính toán histogram của vùng xung quanh pixel (x, y) cho từng kênh màu
                int[] histogramR = calculateHistogram(data, x, y, windowSize, 'R');
                int[] histogramG = calculateHistogram(data, x, y, windowSize, 'G');
                int[] histogramB = calculateHistogram(data, x, y, windowSize, 'B');

                // Giới hạn độ tương phản của histogram
                clipHistogram(histogramR, clipLimit);
                clipHistogram(histogramG, clipLimit);
                clipHistogram(histogramB, clipLimit);

                // Tính toán histogram tích lũy cho từng kênh màu
                int[] cumulativeHistogramR = calculateCumulativeHistogram(histogramR);
                int[] cumulativeHistogramG = calculateCumulativeHistogram(histogramG);
                int[] cumulativeHistogramB = calculateCumulativeHistogram(histogramB);

                // Tính toán CDF min và CDF max cho từng kênh màu
                int cdfMinR = cumulativeHistogramR[0];
                int cdfMinG = cumulativeHistogramG[0];
                int cdfMinB = cumulativeHistogramB[0];

                // Lấy giá trị màu của pixel (x, y)
                int r = data[y][x].getR();
                int g = data[y][x].getG();
                int b = data[y][x].getB();

                // Tính toán giá trị mới cho từng kênh màu
                int newR = calculateNewValue(r, cumulativeHistogramR, cdfMinR, windowSize);
                int newG = calculateNewValue(g, cumulativeHistogramG, cdfMinG, windowSize);
                int newB = calculateNewValue(b, cumulativeHistogramB, cdfMinB, windowSize);

                // Đặt giá trị mới cho pixel (x, y) trong bức ảnh kết quả
                result.setRGB(x, y, (newR << 16) | (newG << 8) | newB);
            }
        }

        return result;
    }

    private static void clipHistogram(int[] histogram, int clipLimit) {
        // Tính toán tổng số lượng pixel vượt quá ngưỡng cho toàn bộ histogram
        int excess = 0;
        for (int count : histogram) {
            if (count > clipLimit) {
                excess += count - clipLimit;
            }
        }

        // Phân chia sự dư thừa đồng đều vào tất cả các bins
        int addedExcess = excess / CLAHE_BUFFER_SIZE;

        // Giảm số lượng pixel vượt quá ngưỡng cho mỗi bin
        for (int i = 0; i < CLAHE_BUFFER_SIZE; i++) {
            if (histogram[i] > clipLimit) {
                histogram[i] = clipLimit;
            } else {
                if (histogram[i] + addedExcess <= clipLimit) {
                    histogram[i] += addedExcess;
                } else {
                    histogram[i] = clipLimit;
                }
            }
        }
    }

    private static int[] calculateHistogram(Pixel[][] data, int x, int y, int windowSize, char color) {
        int width = data[0].length;
        int height = data.length;
        int[] histogram = new int[CLAHE_BUFFER_SIZE];
        int halfWindowSize = windowSize / 2;
        int startX = Math.max(0, x - halfWindowSize);
        int startY = Math.max(0, y - halfWindowSize);
        int endX = Math.min(width - 1, x + halfWindowSize);
        int endY = Math.min(height - 1, y + halfWindowSize);

        // Duyệt qua vùng xung quanh pixel (x, y)
        for (int j = startY; j <= endY; j++) {
            for (int i = startX; i <= endX; i++) {
                Pixel pixel = data[j][i];
                int pixelValue = switch (color) {
                    case 'R' -> pixel.getR();
                    case 'G' -> pixel.getG();
                    case 'B' -> pixel.getB();
                    default -> throw new IllegalArgumentException("Invalid color channel: " + color);
                };
                histogram[pixelValue]++;
            }
        }

        return histogram;
    }

    private static int[] calculateCumulativeHistogram(int[] histogram) {
        int[] cumulativeHistogram = new int[CLAHE_BUFFER_SIZE];
        cumulativeHistogram[0] = histogram[0];

        // Tính toán histogram tích lũy
        for (int i = 1; i < CLAHE_BUFFER_SIZE; i++) {
            cumulativeHistogram[i] = cumulativeHistogram[i - 1] + histogram[i];
        }

        return cumulativeHistogram;
    }

    private static int calculateNewValue(int color, int[] cumulativeHistogram, int cdfMin, int windowSize) {
        double cdfNorm = (double) (cumulativeHistogram[color] - cdfMin) / (windowSize * windowSize - cdfMin);
        int newValue = (int) (cdfNorm * 255);
        return newValue;
    }
}
