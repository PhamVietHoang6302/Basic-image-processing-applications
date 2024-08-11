/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.awt.image.BufferedImage;

/**
 *
 * @author phamv
 */
public class ConvertImageTonegative {

    public static BufferedImage convertTonegative(BufferedImage img) {
        BufferedImage zeroone = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Pixel[][] tmp = DataImage.getImageData(img);
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                int red = 255 - tmp[i][j].getR();
                int green = 255 - tmp[i][j].getG();
                int blue = 255 - tmp[i][j].getB();
                int grayRGB = (red << 16) | (green << 8) | blue;
                zeroone.setRGB(j, i, grayRGB);
            }
        }
        return zeroone;
    }
}
