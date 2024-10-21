/** @author henrik.tramberend@bht-berlin.de */
package cgg;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cgtools.*;

public class Image {
    private double[] pixels;
    private int width;
    private int height;
    private double gamma = 2.2; // Gammawert
    private int samplesPerPixel; // Anzahl der Abtastpunkte pro Pixel

    public Image(int width, int height, int samplesPerPixel) {
        this.width = width;
        this.height = height;
        this.samplesPerPixel = samplesPerPixel;
        pixels = new double[width * height * 3]; // 3 Komponenten (RGB) pro Pixel
    }

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new double[width * height * 3]; // 3 Komponenten (RGB) pro Pixel
    }

    public void setPixel(int x, int y, Color color) {
        int index = (y * width + x) * 3; // Berechnung des Speicherindex i
        pixels[index] = Math.pow(color.r(), 1.0 / gamma);
        pixels[index + 1] = Math.pow(color.g(), 1.0 / gamma);
        pixels[index + 2] = Math.pow(color.b(), 1.0 / gamma);
    }

    public void sample(Sampler sampler) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = stratifiedSampling(sampler, x, y);
                setPixel(x, y, color);
            }
        }
    }

    private Color stratifiedSampling(Sampler sampler, int x, int y) {
        Color result = new Color(0, 0, 0);
        int gridSamples = (int) Math.sqrt(samplesPerPixel); // Teilt ein Pixel in ein gleichmäßiges Gitter
        for (int i = 0; i < gridSamples; i++) {
            for (int j = 0; j < gridSamples; j++) {
                double offsetX = (i + Random.random()) / gridSamples;
                double offsetY = (j + Random.random()) / gridSamples;
                Color sampleColor = sampler.getColor(x + offsetX, y + offsetY);
                result = new Color(
                        result.r() + sampleColor.r(),
                        result.g() + sampleColor.g(),
                        result.b() + sampleColor.b()
                );
            }
        }
        return new Color(
                result.r() / samplesPerPixel,
                result.g() / samplesPerPixel,
                result.b() / samplesPerPixel
        );
    }


    public double[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void write(String filename) {
        cgtools.ImageWriter.write(filename,pixels,width,height);
    }
    //Mit Hilfe von ChatGPT
     public void parallelization(Sampler sampler, int threadCount, int samplePerPixel) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Void>> futures = new ArrayList<>();

        int rowsPerThread = height / threadCount;

        for (int i = 0; i < threadCount; i++) {
            final int startRow = i * rowsPerThread;
            final int endRow = (i == threadCount - 1) ? height : (i + 1) * rowsPerThread;

            futures.add(executor.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (int y = startRow; y < endRow; y++) {
                        for (int x = 0; x < width; x++) {
                            Color color = stratifiedSampling(sampler, x, y);
                            setPixel(x, y, color);
                        }
                    }
                    return null;
                }
            }));
        }

        for (Future<Void> future : futures) {
            future.get();
        }

        executor.shutdown();
    }
}