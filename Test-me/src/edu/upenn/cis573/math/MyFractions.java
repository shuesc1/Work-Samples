
package edu.upenn.cis573.math;

import java.math.BigInteger;

import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.util.ArithmeticUtils;

public class MyFractions {

	/*
	 * DO NOT CHANGE THIS CODE!
	 * Please notify the instructor if you feel that it is necessary to do so.
	 */

    /**
     * Implement add and subtract using algorithm described in Knuth 4.5.1.
     *
     * @param f1 the first fraction to add or subtract, must not be {@code null}
     * @param f2 the second fraction to add or subtract, must not be {@code null}
     * @param isAdd true to add, false to subtract
     * @return a {@code Fraction} instance with the resulting values
     * @throws NullArgumentException if the fraction is {@code null}
     * @throws MathArithmeticException if the resulting numerator or denominator
     *   cannot be represented in an {@code int}.
     */
    public static Fraction addSub(Fraction f1, Fraction f2, boolean isAdd) {
        if (f1 == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (f2 == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        // zero is identity for addition.
        if (f1.getNumerator() == 0) {
        	if (isAdd)
        		return f2;
        	else return f2.negate();
        }
        if (f2.getNumerator() == 0) {
            return f1;
        }
        int d1 = ArithmeticUtils.gcd(f1.getDenominator(), f2.getDenominator());
        if (d1==1) {
            // result is ( (u*v' +/- u'v) / u'v')
            int uvp = ArithmeticUtils.mulAndCheck(f1.getNumerator(), f2.getDenominator());
            int upv = ArithmeticUtils.mulAndCheck(f2.getNumerator(), f1.getDenominator());
            return new Fraction
                (isAdd ? ArithmeticUtils.addAndCheck(uvp, upv) :
                 ArithmeticUtils.subAndCheck(uvp, upv),
                 ArithmeticUtils.mulAndCheck(f1.getDenominator(), f2.getDenominator()));
        }
        // the quantity 't' requires 65 bits of precision; see knuth 4.5.1
        // exercise 7.  we're going to use a BigInteger.
        // t = u(v'/d1) +/- v(u'/d1)
        BigInteger uvp = BigInteger.valueOf(f1.getNumerator())
        .multiply(BigInteger.valueOf(f2.getDenominator()/d1));
        BigInteger upv = BigInteger.valueOf(f2.getNumerator())
        .multiply(BigInteger.valueOf(f1.getDenominator()/d1));
        BigInteger t = null;
        if (isAdd) 
        	t = uvp.add(upv);
        else t = uvp.subtract(upv);
        // but d2 doesn't need extra precision because
        // d2 = gcd(t,d1) = gcd(t mod d1, d1)
        int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
        int d2 = d1;
        if (tmodd1 != 0)
        	d2 = ArithmeticUtils.gcd(tmodd1, d1);

        // result is (t/d2) / (u'/d1)(v'/d2)
        BigInteger w = t.divide(BigInteger.valueOf(d2));
        if (w.bitLength() > 31) {
            throw new MathArithmeticException(LocalizedFormats.NUMERATOR_OVERFLOW_AFTER_MULTIPLY,
                                              w);
        }
        return new Fraction (w.intValue(),
                ArithmeticUtils.mulAndCheck(f1.getDenominator()/d1,
                        f2.getDenominator()/d2));
    }

}
