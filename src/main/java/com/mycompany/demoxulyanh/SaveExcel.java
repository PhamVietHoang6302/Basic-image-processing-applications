/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveExcel {

    public static void exportToCSV(ArrayList<EvaluateImage> imageList) {
        // Lấy đường dẫn thư mục hiện tại
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "data.csv";

        // Tạo đối tượng FileWriter
        try (FileWriter writer = new FileWriter(filePath)) {

            writer.append("Image Name");
            writer.append(",");
            writer.append("Average Brightness");
            writer.append(",");
            writer.append("Contrast");
            writer.append(",");
            writer.append("Information Amount");
            writer.append(",");
            writer.append("Sharpness");
            writer.append("\n");

            // Viết dữ liệu từ danh sách EvaluateImage vào tệp CSV
            for (EvaluateImage image : imageList) {
                writer.append(image.getName());
                writer.append(",");
                writer.append(image.getDosangtrungbinh());
                writer.append(",");
                writer.append(image.getDotuongphan());
                writer.append(",");
                writer.append(image.getLuongthongtin());
                writer.append(",");
                writer.append(image.getDosacnet());
                writer.append("\n");
            }
            writer.append("\n");
            System.out.println("Dữ liệu đã được xuất ra file CSV thành công.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
