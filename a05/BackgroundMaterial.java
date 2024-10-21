package cgg.a05;

import cgtools.*;

public class BackgroundMaterial implements Material{
	public Ray scatteredRay(Ray ray, Hit hit) {
		return null;
	}

	public Color albedoCol(Ray ray, Hit hit) {
		return null;
	}

	public Color emission(Ray ray, Hit hit) {
		return Vector.white;
	}
}