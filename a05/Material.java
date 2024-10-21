package cgg.a05;

import cgtools.Color;

public interface Material {
	/**
     * Berechnet den gestreuten Strahl nach dem Auftreffen auf das Material.
     *
     * @param ray der eingehende Strahl.
     * @param hit die Hit Informationen.
     * @return das scattered ray.
     */
	public Ray scatteredRay(Ray ray, Hit hit);
	/**
     * Gets the albedo of the material at the hit point.
     *
     * @param ray der eingehende Strahl.
     * @param hit die Hit Informationen.
     * @return die albedo color.
     */
	public Color albedoCol(Ray ray, Hit hit);
	 /**
     * Gets the emitted radiance of the material at the hit point.
     *
     * @param ray der eingehende Strahl.
     * @param hit die Hit Informationen.
     * @return die emitted color.
     */
	public Color emission(Ray ray, Hit hit);
}