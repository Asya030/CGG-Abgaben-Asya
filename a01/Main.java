/** @author henrik.tramberend@bht-berlin.de */
package cgg.a01;

import static cgtools.Vector.*;

public class Main {

  public static void main(String[] args) {
    final int width = 512;
    final int height = 256;

    // This class instance defines the contents of the image.
    //ConstantColor content = new ConstantColor(black);
    PolkaDots content = new PolkaDots(red,black,10,10);

    // Creates an image and iterates over all pixel positions inside the image.
    Image1 image = new Image1(width, height);
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Sets the color for one particular pixel.
        image.setPixel(x, y, content.getColor(x, y));
      }
    }

    // Write the image to disk.
    final String filename = "doc/a01-polka-dots.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
