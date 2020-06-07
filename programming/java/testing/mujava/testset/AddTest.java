import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class AddTest {

	
    @Test
    public void testPositivePositive() {
	Adder a = new Adder();
	int sum = a.add(5, 9);
	assertEquals(14, sum);
    }

    @Test
    public void testNegativeNegative() {
	Adder a = new Adder();
	int sum = a.add(-5, -9);
	assertEquals(-14, sum);
    }
}
