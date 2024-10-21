package cgg.a06;

public class Group implements Shape {
    Shape[] group;

    public Group(Shape[] group) {
        this.group = group;
    }

    public Hit intersect(Ray ray) {
        Hit a = null;
        for (Shape c : group) {
            Hit b = c.intersect(ray);
            if (b != null)
                if (a == null) {
                    a = b;
                } else {
                    if (a.t > b.t) {
                        a = b;
                    }
                }
        }
        return a;
    }
}

