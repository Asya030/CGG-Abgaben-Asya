package cgg.a05;

import cgg.Image;
import cgtools.*;

public class Raytracer implements Sampler{
	public Camera camera;
	public Group group;
	public int rate;
	
	public Raytracer(Camera camera, Group group, int n, int width, int height, String filename) {
		this.camera = camera;
		this.group = group;
		this.rate = n;
		Image image = new Image(width, height, rate);
	
		
		for(int x = 0; x != width; x++) {
    		for(int y = 0; y != height; y++) {
    			Color c = getColor(x,y);
    			image.setPixel(x, y, c);
    		}
		}	
		image.write(filename);
        System.out.println("Wrote image: " + filename);	
	}
	
	public Color getColor(double x, double y) {	
		double r = 0;
		double g = 0;
		double b = 0;

		for(int xi=0; xi<rate; xi++) {
			for(int yi=0; yi<rate; yi++) {
				double rx = Random.random();
				double ry = Random.random();
				double xs = x+(xi+rx)/rate;
				double ys = y+(yi+ry)/rate;
				Color resultColor = calcRadiance(group, camera.rayCalc(xs, ys),5);
				r += resultColor.r();
				g += resultColor.g();
				b += resultColor.b();
			}
		}
		return new Color(Math.pow(r/(rate*rate),1/2.2), Math.pow(g/(rate*rate),1/2.2), Math.pow(b/(rate*rate),1/2.2)); 
	}
	
	static Color calcRadiance(Shape scene,Ray ray, int depth) { 
		//Überprüfung der maximalen Rekursionstiefe 
		if(depth == 0) {
			return Vector.black;
		}
		
		Hit hit = scene.intersect(ray);
		
		Ray scattered = hit.material.scatteredRay(ray, hit);
		if (scattered != null){
			Color ea = Vector.add(hit.material.emission(ray, hit), hit.material.albedoCol(ray, hit));
			return Vector.multiply(ea, calcRadiance(scene, scattered, depth-1));
		}else 
		{
			return hit.material.emission(ray, hit);
		}
	} 
}