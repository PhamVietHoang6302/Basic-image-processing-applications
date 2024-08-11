/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author PhamVietHoang
 */
public class LoadallImage {

    public static ArrayList<EvaluateImage> loadImagesFromFolder(String folderPath) {
        ArrayList<EvaluateImage> images = new ArrayList<>();

        // Tạo đối tượng File cho thư mục chứa ảnh
        File folder = new File(folderPath);

        // Lấy danh sách các tệp trong thư mục
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            // Duyệt qua từng tệp trong danh sách
            for (File file : listOfFiles) {
                // Kiểm tra nếu là tệp hình ảnh
                if (file.isFile() && isImageFile(file.getName())) {
                    try {
                        // Đọc ảnh từ tệp và thêm vào ArrayList
                        BufferedImage image = ImageIO.read(file);
                        images.add(new EvaluateImage(file.getName(), image));
                    } catch (IOException e) {
                        // Xử lý ngoại lệ nếu có lỗi khi đọc ảnh
                        e.printStackTrace();
                    }
                }
            }
        }

        return images;
    }

    // Phương thức kiểm tra xem một tên tệp có phải là tệp hình ảnh không
    private static boolean isImageFile(String fileName) {
        String[] imgExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        for (String ext : imgExtensions) {
            if (fileName.toLowerCase().endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }
}
