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
public class EvaluateImage {

    private String name;
    private String Dosangtrungbinh = "1";
    private String Dotuongphan = "1";
    private String Luongthongtin = "1";
    private String Dosacnet = "1";
    private BufferedImage img;

    public EvaluateImage(String name, BufferedImage img) {
        this.name = name;
        this.img = img;
    }

    public EvaluateImage(String name, String Dosangtrungbinh, String Dotuongphan, String Luongthongtin, String Dosacnet) {
        this.name = name;
        this.Dosangtrungbinh = Dosangtrungbinh;
        this.Dotuongphan = Dotuongphan;
        this.Luongthongtin = Luongthongtin;
        this.Dosacnet = Dosacnet;
    }

    public EvaluateImage(String name, String Dosangtrungbinh, String Dotuongphan, String Luongthongtin, String Dosacnet, BufferedImage img) {
        this.name = name;
        this.Dosangtrungbinh = Dosangtrungbinh;
        this.Dotuongphan = Dotuongphan;
        this.Luongthongtin = Luongthongtin;
        this.Dosacnet = Dosacnet;
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosangtrungbinh() {
        return Dosangtrungbinh;
    }

    public void setDosangtrungbinh(String Dosangtrungbinh) {
        this.Dosangtrungbinh = Dosangtrungbinh;
    }

    public String getDotuongphan() {
        return Dotuongphan;
    }

    public void setDotuongphan(String Dotuongphan) {
        this.Dotuongphan = Dotuongphan;
    }

    public String getLuongthongtin() {
        return Luongthongtin;
    }

    public void setLuongthongtin(String Luongthongtin) {
        this.Luongthongtin = Luongthongtin;
    }

    public String getDosacnet() {
        return Dosacnet;
    }

    public void setDosacnet(String Dosacnet) {
        this.Dosacnet = Dosacnet;
    }

}
