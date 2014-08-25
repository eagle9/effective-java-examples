package org.effectivejava.examples.chapter04.item15;

import java.math.BigInteger;
import java.util.BitSet;
//disadvantage of immutable, new object for each distinct value
//consider its mutable companion class, BitSet for BigInteger, StringBuilder for String
public class ImmutableCost {
	public static void main(String [] args) {
		BigInteger moby = BigInteger.valueOf(1);
		System.out.println(moby);
		long start = System.nanoTime();
		moby = moby.flipBit(0);
		long duration = System.nanoTime() - start;
		System.out.println("duration = " + duration + " moby=" + moby);
		long [] a = {1};
		BitSet b = BitSet.valueOf(a);
		System.out.println(moby);
		start = System.nanoTime();
		b.flip(0);
		duration = System.nanoTime() - start;
		System.out.println("duration = " + duration + " b=" + b);
		/*
		The flipBit method creates a new BigInteger instance, also a million bits long,
		that differs from the original in only one bit. The operation requires time and
		space proportional to the size of the BigInteger. Contrast this to
		java.util.BitSet. Like BigInteger, BitSet represents an arbitrarily long
		sequence of bits, but unlike BigInteger, BitSet is mutable. The BitSet class
		provides a method that allows you to change the state of a single bit of a millionbit
		instance in constant time.
		*/
	}
}
