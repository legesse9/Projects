import java.util.HashMap;

import components.map.Map;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Daniel Legesse
 * @author Mathew Sinadinos
 */
public final class TagCloud {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private TagCloud() {
    }

    /**
     * Inputs a "menu" of lines from the given file and stores them in the given
     * {@code Queue}.
     *
     * @param fileName
     *            the name of the input file
     * @param lineQueue
     *            A Queue of lines in the file
     * @replaces lineQueue
     * @requires <pre>
     * [file named fileName exists but is not open]
     * </pre>
     */
    static void getTerms(String fileName, Queue<String> lineQueue) {
        assert fileName != null : "Violation of: fileName is not null";

        // Extracts terms and definitions line by line from text file
        SimpleReader input = new SimpleReader1L(fileName);
        String lines = "";
        while (!input.atEOS()) {
            lines = input.nextLine();
            if (lines.isEmpty()) {
                lines = input.nextLine();
            }
            lineQueue.enqueue(lines);
        }
        input.close();

    }

    /**
     * Checks every element of line of inputed file and separates words in each
     * line. If word is already in the map, the value increases by 1
     *
     * @param counter
     *            An empty map to be updated
     * @param lineQueue
     *            A Queue with lines from the inputed file
     * @replaces counter
     */
    private static void wordSeperator(Queue<String> lineQueue,
            Map<String, Integer> counter) {
        while (lineQueue.length() > 0) {
            String line = lineQueue.dequeue();
            int i = 0;
            while (line.length() > 0) {
                if (Character.isLetter(line.charAt(i))) {
                    i++;
                } else {
                    String word = line.substring(0, i);
                    line = line.substring(i + 1, line.length());
                    if (counter.hasKey(word)) {
                        int val = counter.value(word);
                        counter.replaceValue(word, val++);
                    } else {
                        counter.add(word, 1);
                    }
                }
            }
        }
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires <pre>
     * [the salaries in map are positive] and raisePercent > 0 and
     * [the dynamic types of map and of all objects reachable from map
     *  (including any objects returned by operations (such as
     *  entrySet() and, from there, iterator()), and so on,
     *  recursively) support all optional operations]
     * </pre>
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    public static void giveRaise(java.util.Map<String, Integer> map,
            char initial, int raisePercent) {
        assert map != null : "Violation of: map is not null";
        assert raisePercent > 0 : "Violation of: raisePercent > 0";

        final int ten = 10;
        for (java.util.Map.Entry<String, Integer> mapTemp : map.entrySet()) {
            if (mapTemp.getKey().startsWith(Character.toString(initial))) {
                int newValue = mapTemp.getValue()
                        + mapTemp.getValue() * raisePercent;
                newValue = newValue / ten;
                mapTemp.setValue(newValue);
            }

        }

    }

    /**
     * . Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        java.util.Map<String, Integer> map = new HashMap<>();
        map.put("snow", 30000);
        out.println(map);
        giveRaise(map, 's', 10);
        out.println(map);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
