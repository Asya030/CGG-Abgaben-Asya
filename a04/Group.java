package cgg.a04;

import java.util.ArrayList;
import java.util.List;

public class Group implements Shape {
    private List<Shape> shapes;

    public Group() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public Hit intersect(Ray r) {
        Hit closestHit = null;
        double closestT = Double.POSITIVE_INFINITY;

        for (Shape shape : shapes) {
            Hit hit = shape.intersect(r);
            if (hit != null && hit.getT() < closestT) {
                closestT = hit.getT();
                closestHit = hit;
            }
        }

        return closestHit;
    }
}
