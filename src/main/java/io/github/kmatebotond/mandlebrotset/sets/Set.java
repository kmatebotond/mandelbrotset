package io.github.kmatebotond.mandlebrotset.sets;

import io.github.kmatebotond.mandlebrotset.complex.Complex;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public abstract class Set {
    protected double[][] grid;
    protected Complex iterationStart;
    protected Complex radiusOfConvergence;
    protected double iterationLimit;
    protected int iterationMaxValue = Integer.MIN_VALUE;
    protected int drawWidthHeight;

    public Set(double realFrom, double realTo, double imaginaryFrom, double imaginaryTo, Complex iterationStart, Complex radiusOfConvergence, int iterationLimit, int drawWidthHeight) {
        Complex[][] complexGrid = new Complex[drawWidthHeight][drawWidthHeight];
        double realStepSize = (realTo - realFrom) / drawWidthHeight;
        double imaginaryStepSize = (imaginaryTo - imaginaryFrom) / drawWidthHeight;
        for (int i = 0; i < drawWidthHeight; i++) {
            double real = realFrom + (i * realStepSize);
            for (int j = 0; j < drawWidthHeight; j++) {
                double imaginary = imaginaryFrom + (j * imaginaryStepSize);
                complexGrid[i][j] = new Complex(real, imaginary);
            }
        }

        this.iterationStart = iterationStart;
        this.radiusOfConvergence = radiusOfConvergence;
        this.iterationLimit = iterationLimit;
        grid = new double[drawWidthHeight][drawWidthHeight];
        for (int i = 0; i < drawWidthHeight; i++) {
            for (int j = 0; j < drawWidthHeight; j++) {
                int iterationValue = iterate(complexGrid[i][j]);
                if (iterationValue > iterationMaxValue) {
                    iterationMaxValue = iterationValue;
                }
                grid[i][j] = iterationValue;
            }
        }

        this.drawWidthHeight = drawWidthHeight;
    }
    public Set(double real, double imaginary, double epsilon, Complex iterationStart, Complex radiusOfConvergence, int iterationLimit, int drawWidthHeight) {
        this(real - epsilon, real + epsilon, imaginary - epsilon, imaginary + epsilon, iterationStart, radiusOfConvergence, iterationLimit, drawWidthHeight);
    }

    protected abstract int iterate(Complex complex);

    public void writeImage(File file) throws IOException {
        BufferedImage image = getImage();
        ImageIO.write(image, "png", file);
    }
    private BufferedImage getImage() {
        BufferedImage image = new BufferedImage(drawWidthHeight, drawWidthHeight, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        for (int i = 0; i < drawWidthHeight; i++) {
            for (int j = 0; j < drawWidthHeight; j++) {
                double[] color = new double[3];
                if (grid[i][j] != iterationMaxValue) {
                    double c = grid[i][j] * (255.0 / iterationMaxValue);
                    color[0] = c;
                    color[1] = c / 2;
                    color[2] = c / 4;
                }
                raster.setPixel(i, j, color);
            }
        }
        return image;
    }
}
