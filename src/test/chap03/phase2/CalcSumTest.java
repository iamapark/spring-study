package chap03.phase2;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author jinyoung.park89
 * @since 7/24/16
 */
public class CalcSumTest {

    private Calculator calculator;
    private String filePath;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
        this.filePath = getClass().getResource("numbers.text").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        Integer sum = this.calculator.calcSum(this.filePath);
        Assert.assertSame(sum, 10);
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        Assert.assertSame(this.calculator.calcMultiply(this.filePath), 24);
    }
}
