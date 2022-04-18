package io.github.kmatebotond.mandlebrotset.complex;

public class Complex {
    protected double real;
    protected double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }
    public void setReal(double real) {
        this.real = real;
    }
    public double getImaginary() {
        return imaginary;
    }
    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }

    // Operations
    public double absoluteValue() {
        return Math.sqrt((real * real) + (imaginary * imaginary));
    }
    public Complex add(Complex complex) {
        return new Complex(real + complex.real, imaginary + complex.imaginary);
    }
    public Complex subtract(Complex complex) {
        return new Complex(real - complex.real, imaginary - complex.imaginary);
    }
    public Complex multiply(Complex complex) {
        return new Complex(
                (real * complex.real) - (imaginary * complex.imaginary),
                (real * complex.imaginary) + (imaginary * complex.real)
        );
    }
    public Complex divide(Complex complex) {
        Complex result = new Complex(
                (real * complex.real) + (imaginary * complex.imaginary),
                (imaginary * complex.real) - (real * complex.imaginary)
        );
        double n = (complex.real * complex.real) + (complex.imaginary * complex.imaginary);
        result.real /= n;
        result.imaginary /= n;
        return result;
    }
    public Complex sin() {
        return new Complex(
                Math.sin(real) * Math.cosh(imaginary),
                Math.cos(real) * Math.sinh(imaginary)
        );
    }
}
