/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoxulyanh;

/**
 *
 * @author PhamVietHoang
 */
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SaveImage {

    public static void exportImage(BufferedImage image, String name) {
        // Tạo đường dẫn đến thư mục ImageSave nằm cùng thư mục với mã nguồn
        File directory = new File("ImageSave");
        if (!directory.exists()) {
            directory.mkdir(); // Tạo thư mục nếu chưa tồn tại
        } 
        String fileName = name + "Edit.png";
        File output = new File(directory, fileName);

        try {
            ImageIO.write(image, "PNG", output); // Xuất ảnh dưới định dạng PNG
            System.out.println("Image exported successfully to: " + output.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error exporting image: " + e.getMessage());
        }
    }
}
