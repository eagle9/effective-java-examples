// Immutable class - pages 76-78
package org.effectivejava.examples.chapter04.item15;

//class ComplexSub extends Complex {};

//even without making Complex final, withing its constructor private, 
//  you can not extend Complex
//public final class Complex {
public class Complex {
	private final double re;
	private final double im;
	
	//note the constructor is made private
	private Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	// Immutable class with static factories instead of constructors
	public static Complex valueOf(double re, double im) {
		return new Complex(re, im);
	}

	/*
	 * This would be very messy using constructors
because the natural constructor would have the same signature that we
already used: Complex(double, double). With static factories it’s easy. Just add
a second static factory with a name that clearly identifies its function
	 */
	public static Complex valueOfPolar(double r, double theta) {
		return new Complex(r * Math.cos(theta), r * Math.sin(theta));
	}

	public static final Complex ZERO = new Complex(0, 0);
	public static final Complex ONE = new Complex(1, 0);
	public static final Complex I = new Complex(0, 1);

	// Accessors with no corresponding mutators
	public double realPart() {
		return re;
	}

	public double imaginaryPart() {
		return im;
	}

	public Complex add(Complex c) {
		return new Complex(re + c.re, im + c.im);
	}

	public Complex subtract(Complex c) {
		return new Complex(re - c.re, im - c.im);
	}

	public Complex multiply(Complex c) {
		return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
	}

	public Complex divide(Complex c) {
		double tmp = c.re * c.re + c.im * c.im;
		return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re
				* c.im)
				/ tmp);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Complex))
			return false;
		Complex c = (Complex) o;

		// See page 43 to find out why we use compare instead of ==
		return Double.compare(re, c.re) == 0 && Double.compare(im, c.im) == 0;
	}

	@Override
	public int hashCode() {
		int result = 17 + hashDouble(re);
		result = 31 * result + hashDouble(im);
		return result;
	}

	private int hashDouble(double val) {
		long longBits = Double.doubleToLongBits(re);
		return (int) (longBits ^ (longBits >>> 32));
	}

	@Override
	public String toString() {
		return "(" + re + " + " + im + "i)";
	}
}
