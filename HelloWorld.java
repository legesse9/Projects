import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program
 *
 * @author Daniel Legesse
 *
 */
public class HelloWorld {
    /**
     * No-argument constructor--private to prevent instantiation.
     */

    private HelloWorld() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleWriter out = new SimpleWriter1L();
        out.println("Hello World!");
        out.close();

    }

}
