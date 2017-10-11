package edu.upenn.cis573.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;

public class MySorter {

	/*
	 * DO NOT CHANGE THIS CODE!
	 * Please notify the instructor if you feel that it is necessary to do so.
	 */

	/**
     * Sort an array in place and perform the same reordering of entries on
     * other arrays.  
     *
     * @param x Array to be sorted and used as a pattern for permutation
     * of the other arrays.
     * @param dir Order direction.
     * @param yList Set of arrays whose permutations of entries will follow
     * those performed on {@code x}.
     * @throws DimensionMismatchException if any {@code y} is not the same
     * size as {@code x}.
     * @throws NullArgumentException if {@code x} or any {@code y} is null
     */
    public static void sortInPlace(double[] x,
                                   final OrderDirection dir,
                                   double[] ... yList)
        throws NullArgumentException,
               DimensionMismatchException {

        // Consistency checks.
        if (x == null) {
            throw new NullArgumentException();
        }

        final int yListLen = yList.length;
        final int len = x.length;

        for (int j = 0; j < yListLen; j++) {
            final double[] y = yList[j];
            if (y == null) {
                throw new NullArgumentException();
            }
            if (y.length != len) {
                throw new DimensionMismatchException(y.length, len);
            }
        }

        // Associate each abscissa "x[i]" with its index "i".
        final List<PairDoubleInteger> list
            = new ArrayList<PairDoubleInteger>(len);
        for (int i = 0; i < len; i++) {
            list.add(new PairDoubleInteger(x[i], i));
        }

        // Create comparators for increasing and decreasing orders.
        final Comparator<PairDoubleInteger> comp
            = dir == OrderDirection.INCREASING ?
            new Comparator<PairDoubleInteger>() {

            public int compare(PairDoubleInteger o1,
                               PairDoubleInteger o2) {
                return Double.compare(o1.getKey(), o2.getKey());
            }
        } : new Comparator<PairDoubleInteger>() {

        	public int compare(PairDoubleInteger o1,
                               PairDoubleInteger o2) {
                return Double.compare(o2.getKey(), o1.getKey());
            }
        };

        // Sort.
        Collections.sort(list, comp);

        // Modify the original array so that its elements are in
        // the prescribed order.
        // Retrieve indices of original locations.
        final int[] indices = new int[len];
        for (int i = 0; i < len; i++) {
            final PairDoubleInteger e = list.get(i);
            x[i] = e.getKey();
            indices[i] = e.getValue();
        }

        // In each of the associated arrays, move the
        // elements to their new location.
        for (int j = 0; j < yListLen; j++) {
            // Input array will be modified in place.
            final double[] yInPlace = yList[j];
            final double[] yOrig = yInPlace.clone();

            for (int i = 0; i < len; i++) {
                yInPlace[i] = yOrig[indices[i]];
            }
        }
    }
    
    /**
     * A helper data structure holding a double and an integer value.
     */
    public static class PairDoubleInteger {
        /** Key */
        private final double key;
        /** Value */
        private final int value;

        /**
         * @param key Key.
         * @param value Value.
         */
        PairDoubleInteger(double key, int value) {
            this.key = key;
            this.value = value;
        }

        /** @return the key. */
        public double getKey() {
            return key;
        }

        /** @return the value. */
        public int getValue() {
            return value;
        }
    }
    
    public enum OrderDirection {
        /** Constant for increasing direction. */
        INCREASING,
        /** Constant for decreasing direction. */
        DECREASING
    }
}
