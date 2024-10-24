package cgg.a08;

import cgtools.Matrix;

public class Transform {
    Matrix toWorld;
    Matrix fromWorld;
    Matrix toWorldN;

    Transform(Matrix toWorld) {
        this.toWorld = toWorld;
        this.fromWorld = Matrix.invert(toWorld);
        this.toWorldN = Matrix.transpose(toWorld);
    }

    Ray transform(Ray ray) {
        return new Ray(Matrix.multiply(fromWorld, ray.origin()), Matrix.multiply(fromWorld, ray.d()), ray.tmin(), ray.tmax());
    }

    Hit transform(Hit hit) {
        return new Hit(hit.strahlparameterT(), Matrix.multiply(toWorld, hit.trefferpunktX()), Matrix.multiply(toWorld, hit.normalenvektor()), hit.material());
    }

}