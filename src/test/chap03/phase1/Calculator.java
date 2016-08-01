package chap03.phase1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jinyoung.park89
 * @since 7/24/16
 */
public class Calculator {

    public Integer calcSum(String filepath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        Integer sum = 0;
        String line;
        while ((line = br.readLine()) != null) {
            sum += Integer.valueOf(line);
        }

        br.close();
        return sum;
    }
}
