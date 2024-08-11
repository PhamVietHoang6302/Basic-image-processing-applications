/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author phamv
 */
public class panelImage extends javax.swing.JPanel {

    private final String filename;
    private BufferedImage bf;

    public panelImage(String filename) {
        initComponents();
        this.filename = filename;

    }

    public void loadImage() {
        if (filename == null) {
            return;
        }
        try {
            File file = new File(filename);
            if (!file.exists()) {
                throw new IOException("File not found: " + filename);
            }

            BufferedImage img = ImageIO.read(file);

            // Kiểm tra xem ảnh có kích thước hợp lệ không
            if (img.getWidth() <= 0 || img.getHeight() <= 0) {
                throw new IOException("Invalid image size: " + img.getWidth() + "x" + img.getHeight());
            }

            // Khởi tạo bi
            bf = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D bicontext = bf.createGraphics();
            bicontext.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            bicontext.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
            bicontext.dispose();

            repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 file ảnh!!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + e.getMessage());
        }
    }

    public BufferedImage getBf() {
        return bf;
    }

    public void setBf(BufferedImage bf) {
        this.bf = bf;
    }

    public void repaintImage(BufferedImage Img)  {

        BufferedImage img = Img;
        if (img.getWidth() <= 0 || img.getHeight() <= 0) {
            try {
                throw new IOException("Invalid image size: " + img.getWidth() + "x" + img.getHeight());
            } catch (IOException ex) {
                Logger.getLogger(panelImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Khởi tạo bi
        bf = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D bicontext = bf.createGraphics();
        bicontext.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        bicontext.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
        bicontext.dispose();

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bf != null) {
            g.drawImage(bf, 0, 0, this);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Hiện không có ảnh nào!", 10, 20);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
