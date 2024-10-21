package cgg.a06;

import cgtools.*;

public class BackgroundMaterial implements Material{

	private Color backgroundColor;

    public BackgroundMaterial(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
	public Boolean absorb() {
		return true;
	}

	public Ray scatteredRay(Ray ray, Hit hit) {
		return null;
	}

	public Color albedoCol(Ray ray, Hit hit) {
		return Vector.black;
	}

	public Color emission(Ray ray, Hit hit) {
		return backgroundColor;
	}
}
