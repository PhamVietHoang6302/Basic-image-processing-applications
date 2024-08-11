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
public class ConvertColorToGray {

    public static BufferedImage convertColorToGray(BufferedImage img) {
        BufferedImage graybi = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Pixel[][] tmp = DataImage.getImageData(img);
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                int grayValue = (int) (0.299 * tmp[i][j].getR() + 0.587 * tmp[i][j].getG() + 0.114 * tmp[i][j].getB());
                int grayRGB = (grayValue << 16) | (grayValue << 8) | grayValue;
                graybi.setRGB(j, i, grayRGB);
            }
        }
        return graybi;
    }

}
