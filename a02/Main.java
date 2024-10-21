package cgg.a02;
import cgg.Image;

public class Main {
    public static void main(String[] args) {
        final int width = 1024;
        final int height = 512;

        // Create a list of random discs
        ColoredDiscs discs = new ColoredDiscs(width, height, 40);

        // Create an image
        Image image = new Image(width, height, 10);
        
        // Sample the image using the colored discs
        image.sample(discs);

        // Write the image to disk
        final String filename = "doc/a02-colored-discs.png";
        image.write(filename);
        System.out.println("Wrote image: " + filename);
    }
}