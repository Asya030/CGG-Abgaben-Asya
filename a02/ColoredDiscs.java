package cgg.a02;
import cgtools.*;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;


class ColoredDiscs implements Sampler {
    SortedSet<Disc> discs;

    ColoredDiscs(int width, int height, int count) {
        discs = new TreeSet<>(Comparator.comparingDouble(d -> d.radius)); // Sortierte Liste nach Radius 
        for (int i = 0; i < count; i++) {
            double radius = Random.random() * 70 + 5; 
            double x = Random.random() * (width - 2 * radius) + radius; 
            double y = Random.random() * (height - 2 * radius) + radius; 
            Color color = new Color(Random.random(), Random.random(), Random.random()); 
            discs.add(new Disc(x, y, radius, color));
        }
    }

    @Override
    public Color getColor(double x, double y) {
        for (Disc disc : discs) {
            if (disc.isPointInDisc(x, y)) {
                return disc.color;
        }
    }
        return new Color(0, 0,0); 
    }
}