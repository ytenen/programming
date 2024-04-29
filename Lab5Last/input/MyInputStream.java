/**
 * The {@code MyInputStream} class provides a simple method to read user input from the console.
 * It uses a {@link java.io.BufferedInputStream} and {@link java.io.BufferedReader} to handle input stream operations.
 */
package input;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyInputStream {

    /**
     * Reads a line of input from the console using a buffered input stream and buffered reader.
     *
     * @return The user-inputted line as a {@code String}.
     */
    public static String Scan() {
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader r = new BufferedReader(new InputStreamReader(bf));
        try {
            return r.readLine();
        } catch (IOException e) {
            return "Ошибка ввода";
        }
    }
}
