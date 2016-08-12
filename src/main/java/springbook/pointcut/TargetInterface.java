package springbook.pointcut;

/**
 * @author jinyoung.park89
 * @since 8/12/16
 */
public interface TargetInterface {
    void hello();
    void hello(String a);
    int minus(int a, int b) throws RuntimeException;
    int plus(int a, int b);
}
