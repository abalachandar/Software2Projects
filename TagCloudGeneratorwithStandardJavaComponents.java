import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generates tag cloud from a given input text.
 *
 * @author Adithya Balachandar and Majed Ahmad
 *
 */
public final class TagCloudGeneratorwithStandardJavaComponents {
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGeneratorwithStandardJavaComponents() {

    }

    /**
     *
     * Creates a Count comparator.
     *
     */
    private static class Count
            implements Comparator<Map.Entry<String, Integer>>, Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Map.Entry<String, Integer> pair1,
                Map.Entry<String, Integer> pair2) {
            return pair1.getValue().compareTo(pair2.getValue());
        }

    }

    /**
     *
     * Creates a alphabetical comparator.
     *
     */
    private static class Alphabetize
            implements Comparator<Map.Entry<String, Integer>>, Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Map.Entry<String, Integer> pair1,
                Map.Entry<String, Integer> pair2) {
            return pair1.getKey().compareTo(pair2.getKey());
        }

    }

    private static String fontGenerator(int maxValue, int minValue, int count) {

        int font = 37;
        if (maxValue > minValue) {
            font *= (count - minValue);
            font /= (maxValue - minValue);
            font /= (maxValue - minValue);
            font /= (11);
        }else {
            font = 48;
        }
        return "f" + font;
    }
    /**
     * Creates the table of words and its occurences on an HTML page.
     *
     * @param outputFile
     *            the output file that the table is put on and shown to the
     *            user.
     * @param inputTitle
     *            the title of the input file
     * @param numbers
     *            number of words in the Tag generator
     * @param countTable
     *            the {@code Map} that's being printed out in a table
     * @param sort
     *            the individual words contained in table being sorted
     * @param out
     *            file being passed in
     */
    private static void generateTable(String outputFile, String inputTitle,
            int numbers, Map<String, Integer> countTable,
            List<Map.Entry<String, Integer>> sort, PrintWriter out) {

        int min = 1;
        int max = 1;
        
        //Creates WebPage
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Top " + numbers + " Words in " + inputTitle
                + "</title>");
        out.println("<link href=\"http://web.cse.ohio-state.edu/software/2231"
                + "/web-sw2/assignments/projects/tag-cloud-generation/data"
                + "/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println(
                "<link href=\"doc/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");

        //makes header
        out.println("<body>");
        out.println("<h2>Top " + numbers + " Words in " + inputTitle + "</h2>");
        out.println("<hr>");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");

        //HTML for sorted words
        int position = sort.size();
        while (sort.size() > 0) {
            Entry<String, Integer> word = sort.remove(position);
            out.println("<span style=\"cursor:default\" class=\""
                    + fontGenerator(min,max,word.getValue())
                    + "\" title=\"count: " + word.getValue() + "\">"
                    + word.getKey() + "</span>");

            position--;
        }
        //closing html out
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    /**
     * Counts the number of occurrences of each word in the inputed text.
     *
     * @param input
     *            the text file inputed by the user
     * @param table
     *            the {@code Map} that pairs the word with its number of
     *            Occurrences
     * @param out
     *            the output of the file
     * @throws IOException
     */
    public static void wordCounter(BufferedReader input,
            Map<String, Integer> table, PrintWriter out) {

        //initializes separators that may interfere with word count putting them
        //into a set
        final String seperators = " /n,.!?-@#$%^&*()_:'[]";

        while (input != null) {
            String line;
            try {
                line = input.readLine();
                int i = 0;
                while (line != null && i < line.length()) {
                    //initializes word to either a string or separator
                    String word = nextWordOrSeparator(line, i);
                    //if word is already a key, its value increases, if not a key
                    //is created for it
                    if (table.containsKey(word)) {
                        table.replace(word, table.get(word) + 1);
                    } else if (!(seperators.indexOf(word.charAt(0)) >= 0)) {
                        table.put(word, 1);
                    }
                    //moves position
                    i += word.length();
                }
            } catch (IOException e) {
                System.err.println("End of Stream found");
                return;
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position) {
        assert text != null : "Violation of: text is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        final String seperators = " /n,.!?-@#$%^&*()_:'[];";

        String result = "";
        char character = text.charAt(position);
        int i = position;
        if (seperators.indexOf(character) >= 0) {
            while (i < text.length()
                    && seperators.indexOf(text.charAt(i)) >= 0) {
                character = text.charAt(i);
                result += character;
                i++;
            }
        } else {
            while (i < text.length()
                    && !(seperators.indexOf(text.charAt(i)) >= 0)) {
                character = text.charAt(i);
                result += character;
                i++;
            }
        }
        return result;
    }

    /**
     * organizes a given map in alphabetical order.
     *
     * @param words
     *            words in inputed text along with each number of occurrences.
     * @param numWrds
     * @return organized {@code Queue} in alphabetical order.
     */
    private static List<Entry<String, Integer>> sort(Map<String, Integer> words,
            int numWrds) {

        List<Map.Entry<String, Integer>> sortNum = new ArrayList<>();
        List<Map.Entry<String, Integer>> sortWords = new ArrayList<>();

        //inputs each word into queue
        for (Map.Entry<String, Integer> temp : words.entrySet()) {
            sortNum.add(temp);
        }
        int sz = sortNum.size();
        if (numWrds > sz) {
            for (int i = 0; i < sz; i++) {

                Map.Entry<String, Integer> temp = sortNum.remove(0);
                sortWords.add(temp);
            }
        } else {
            for (int i = 0; i < numWrds; i++) {
                Map.Entry<String, Integer> temp = sortNum.remove(0);

                sortWords.add(temp);
            }
        }

        Comparator<Map.Entry<String, Integer>> numComp = new Count();
        Comparator<Map.Entry<String, Integer>> wordComp = new Alphabetize();
        sortNum.sort(wordComp);
        sortWords.sort(numComp);

        return sortWords;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     * @throws IOException
     */
    public static void main(String[] args) {

        BufferedReader inFile;
        PrintWriter out;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

        //asks for user input file and output file name
        System.out.println("Input file name");
        String input = "";
        try {
            input = in.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input" + e);
            return;
        }

        System.out.println("Enter the name of an output file");
        String output = "";
        try {
            output = in.readLine();
        } catch (IOException e) {
            System.err.println("Error opening in file to out file" + e);
            return;
        }
        System.out.println("Enter a number of words to be included");
        int wordsNum = 0;

        try {
            wordsNum = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.err.println("Incorrect format, error reading input" + e);
            return;
        }

        try {
            inFile = new BufferedReader(new FileReader(input));
        } catch (IOException e) {
            System.err.println("Error opening file");
            return;
        }
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(output)));
        } catch (IOException e) {
            System.err.println("Error opening file");
            try {
                inFile.close();
            } catch (IOException f) {
                System.err.println("Error closing File");
                return;
            }
            return;
        }

        //creates a map for word and its number of occurrences
        Map<String, Integer> table = new HashMap<>();

        //calls upon method to get final result
        wordCounter(inFile, table, out);
        List<Entry<String, Integer>> sorted = sort(table, wordsNum);
        generateTable(output, input, wordsNum, table, sorted, out);

        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
            return;
        }
        out.close();
    }
//data/importance.txt
}
