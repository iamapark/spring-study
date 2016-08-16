package chap06.pointcut;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import springbook.pointcut.Target;

import java.nio.channels.AsynchronousFileChannel;

/**
 * @author jinyoung.park89
 * @since 8/12/16
 */
public class PointcutTest {

    @Test
    public void methodSignaturePointcut() throws Exception {

        String expression = "execution(public int springbook.pointcut.Target.minus(int,int) throws java.lang.RuntimeException)";

        // Target.minus()
        pointcutMatches(expression,true, Target.class, "minus", int.class, int.class);

        // Target.plus()
        pointcutMatches(expression, false, Target.class, "plus", int.class, int.class);
    }

    public void pointcutMatches(String expression, Boolean expected, Class<?> clazz, String methodName, Class<?>... args) throws Exception {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        assertThat(pointcut.getClassFilter().matches(clazz) && pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null), is(expected));
    }
}
