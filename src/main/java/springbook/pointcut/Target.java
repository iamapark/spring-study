package springbook.pointcut;

/**
 * @author jinyoung.park89
 * @since 8/12/16
 */
public class Target implements TargetInterface {
    @Override
    public void hello() {
    }

    @Override
    public void hello(String a) {

    }

    @Override
    public int minus(int a, int b) throws RuntimeException {
        return 0;
    }

    @Override
    public int plus(int a, int b) {
        return 0;
    }

    public void method() {

    }
}
