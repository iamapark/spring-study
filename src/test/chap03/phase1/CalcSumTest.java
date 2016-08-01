package chap03.phase1;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author jinyoung.park89
 * @since 7/24/16
 */
public class CalcSumTest {

    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        Integer sum = calculator.calcSum(getClass().getResource("numbers.text").getPath());
        Assert.assertSame(sum, 10);
    }
}
