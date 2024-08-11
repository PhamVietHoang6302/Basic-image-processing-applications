/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 *
 * @author phamv
 */
public class DataImage {

    public static Pixel[][] getImageData(BufferedImage bi) {
        Pixel[][] data;
        Raster r = bi.getRaster();
        data = new Pixel[r.getHeight()][r.getWidth()];
        int[] temp = new int[3];
        for (int row = 0; row < bi.getHeight(); row++) {
            for (int col = 0; col < bi.getWidth(); col++) {
                temp = r.getPixel(col, row, temp);
                Pixel pixel = new Pixel(temp[0], temp[1], temp[2]);
                data[row][col] = pixel;
            }
        }
        return data;
    }
}
