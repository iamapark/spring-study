package chap03.phase2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author jinyoung.park89
 * @since 7/24/16
 */
public class Calculator {

    public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            T res = initVal;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(res, line);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Integer calcSum(String filepath) throws IOException {
        return lineReadTemplate(filepath, (value, line) -> value + Integer.valueOf(line), 0);
    }

    public int calcMultiply(String filePath) throws IOException {
        return lineReadTemplate(filePath, (value, line) -> value * Integer.valueOf(line), 1);
    }
}
