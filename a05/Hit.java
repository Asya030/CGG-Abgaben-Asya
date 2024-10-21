package cgg.a05;

import cgtools.*;

public class Hit {
	public final double t;
	public final Point x;
	public final Direction n;
	public final Material material;
	
	public Hit(double t, Point x, Direction n, Material material) {
		this.t = t;
		this.x = x;
		this.n = Vector.normalize(n);
		this.material = material;
	}
}