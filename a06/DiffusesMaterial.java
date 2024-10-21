package cgg.a06;

import cgtools.*;

public class DiffusesMaterial implements Material{
	public Color albedoCol;
	
	public DiffusesMaterial(Color albedoCol) {
		this.albedoCol = albedoCol;
	}

	public Boolean absorb() {
		return true;
	}

	public Ray scatteredRay(Ray ray, Hit hit) {
		Direction r = Vector.direction(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);
		Direction d = Vector.normalize(Vector.add(r, hit.n));
		Ray scattered = new Ray(hit.x, d, Double.POSITIVE_INFINITY, 0 );
		return scattered;
	}

	public Color albedoCol(Ray ray, Hit hit) {
		return albedoCol;
	}

	public Color emission(Ray ray, Hit hit) {
		return Vector.black;
	}
}