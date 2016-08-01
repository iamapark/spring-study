package chap03.phase2;

/**
 * @author jinyoung.park89
 * @since 7/24/16
 */
public interface LineCallback<T> {
    T doSomethingWithLine(T value, String line);
}
