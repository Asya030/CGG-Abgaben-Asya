package cgg.a09;

import cgtools.*;;

public class EmittingMaterial implements Material {
    private Color emissionColor;

    public EmittingMaterial(Color emissionColor) {
        this.emissionColor = emissionColor;}

    @Override
    public Ray scattered(Ray ray, Hit hit) {
        return null;
    }

    @Override
    public Color albedo(Ray ray, Hit hit) {
        return Vector.black;  // Keine diffuse Reflexion
    }

    @Override
    public Color emission(Ray ray, Hit hit) {
        return emissionColor; 
    }
}
