package cgg.a09;

import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Sampler;

public class TextureTransform implements Sampler {
    Sampler texture;
    Matrix transform;

    public TextureTransform(Sampler texture, Matrix transform){
        this.texture = texture;
        this.transform = transform;
    }

    public TextureTransform(String filename, Matrix transform){
        this.texture = new Texture(new ImageTexture(filename));
        this.transform = transform;
    }

    @Override
    public Color getColor(double u, double v) {
        Point uv = Matrix.multiply(transform, new Point(u, v, 0));
        return texture.getColor(Math.min(Math.max(uv.x(), 0), 1), Math.min(Math.max(uv.y(), 0), 1));
}
}
