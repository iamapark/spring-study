package chap03.phase2;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author jinyoung.park89
 * @since 7/24/16
 */
public interface BufferedReaderCallback {

    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}
