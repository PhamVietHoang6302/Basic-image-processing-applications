/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.demoxulyanh;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author phamv
 */
public class Histogram extends javax.swing.JPanel {

    /**
     * Creates new form Histogram
     */
    public Histogram() {
        initComponents();
        this.setBackground(Color.black);
    }
    int[] h = new int[256];

    public void setHistogram(int[] h) {
        this.h = h;
        repaint();
    }

    public void setHistogram(Pixel[][] data, int width, int height) {
        this.h = new int[256];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int temp = (data[row][col].getR() + data[row][col].getG() + data[row][col].getB()) / 3;
                this.h[temp]++;
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        for (int i = 0; i < 256; i++) {
            g.drawRect(i, 0, 1, h[i] / 14);
        }
        g.setColor(Color.WHITE);
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
