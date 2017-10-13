Joseph Haymaker
CIS 573 - HW3
Due 10/13/2017

**Class**: MyPrime.java
**Test Class**: MyPrimeNextPrimeTest.java
**Unreachable code**:
1. line 103 - 105 : 
        if (n < 2) {
            return false;
        }
 This condition is not satisfiable due to the fact that this helper method (isPrime()) is being
 called after nextPrime(), which eliminates any number that is less than 2 prior to calling isPrime().
 This helper method would have to be called before lines 56-65 to be able to cover this statement.
 
 2. line 124:
 if (n >= 2047)
 This statement is not fully satisfiable due to the fact that before this in lines 107-111 the number 
 being divided by all prime factors in the PRIMES array making this condition (partially) 
 unsatisfiable.

**Class**: MyFractions.java
**Test Class**: MyFractionsAdSubTest.java
**Unreachable code**:
1. line 47:
if (d1==1)
No valid values supplied would return a greatest common denominator of one, so this statement can't
fully be covered. 
2. lines 59-82:
Couldn't supply any values that would warrant the use of BigInteger.

 **Class**: MySorter.java
**Test Class**: MySorterSortInPlaceTest.java
**Unreachable code**: N/A

