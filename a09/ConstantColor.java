package cgg.a09;
import cgtools.Color;
import cgtools.Sampler;

public class ConstantColor implements Sampler {
    Color color;

    public ConstantColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor(double x, double y) {
        return color;
    }

}
