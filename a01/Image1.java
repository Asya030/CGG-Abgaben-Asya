package cgg.a01;

import cgtools.Color;

public class Image1 {
     private int width;
     private int height;
     private double[] pixels;

    public Image1(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new double[width * height * 3]; //Array für alle Pixel des Bilds mit RGB-Werten
    }

    public void setPixel(int x, int y, Color color) {
        int index = (y * width + x) * 3; //Berechnung der 1D Position des Pixels
        //Setzen der Farbwerte
        pixels[index] = color.r();
        pixels[index + 1] = color.g();
        pixels[index + 2] = color.b();
    }

    public void write(String filename) {
        cgtools.ImageWriter.write(filename,pixels,width,height);
    }
}
