package io.github.kmatebotond.mandlebrotset.sets;

import io.github.kmatebotond.mandlebrotset.complex.Complex;

import java.io.File;
import java.io.IOException;

public class MandelbrotSet extends Set {
    public MandelbrotSet(double realFrom, double realTo, double imaginaryFrom, double imaginaryTo, int iterationLimit, int drawWidthHeight) {
        super(realFrom, realTo, imaginaryFrom, imaginaryTo, new Complex(0, 0), new Complex(2, 0), iterationLimit, drawWidthHeight);
    }
    public MandelbrotSet(double real, double imaginary, double epsilon, int iterationLimit, int drawWidthHeight) {
        this(real - epsilon, real + epsilon, imaginary - epsilon, imaginary + epsilon, iterationLimit, drawWidthHeight);
    }

    @Override
    protected int iterate(Complex complex) {
        Complex result = iterationStart;
        int i;
        for (i = 0; i < iterationLimit; i++) {
            result = result.multiply(result).add(complex);
            if (result.absoluteValue() > radiusOfConvergence.absoluteValue()) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) throws IOException {
        Set mandelbrotSet = new MandelbrotSet(-2.5, 1, -1.75, 1.75, 200, 800);
        mandelbrotSet.writeImage(new File("mandelbrotset.png"));
    }
}
