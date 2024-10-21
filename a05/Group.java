package cgg.a05;

import java.util.ArrayList;
import java.util.Arrays;

public class Group implements Shape {
    public final ArrayList<Shape> shapeList;

    public Group(Shape... liste) {
        this.shapeList = new ArrayList<>();
        shapeList.addAll(Arrays.asList(liste));
    }

    @Override
    public Hit intersect(Ray ray) {
        Hit closeHit = null;

        for (Shape shapes : shapeList) {
            Hit hit = shapes.intersect(ray);
            if (hit != null) {
                if ((closeHit == null) || (hit.t < closeHit.t)) {
                    closeHit = hit;
                }
            }
        }
        return closeHit;
    }
}
