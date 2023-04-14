import java.io.Serializable;
import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Generates tag cloud from a given input text
 *
 * @author Adithya Balachandar and Majed Ahmad
 *
 */
public final class TagCloudGenerator {
    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {

    }

    /**
     *
     * Creates a comparator.
     *
     */
    private static class Count
            implements Comparator<Map.Pair<String, Integer>>, Serializable {
        @Override
        public int compare(Map.Pair<String, Integer> pair1,
                Map.Pair<String, Integer> pair2) {
            return pair2.value().compareTo(pair1.value());
        }

        private static final long serialVersionUID = 1L;
    }

    /**
     *
     * Creates a comparator.
     *
     */
    private static class Alphabetize
            implements Comparator<Map.Pair<String, Integer>>, Serializable {
        @Override
        public int compare(Map.Pair<String, Integer> pair1,
                Map.Pair<String, Integer> pair2) {
            return pair2.key().compareTo(pair1.key());
        }

        private static final long serialVersionUID = 1L;
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
     * @param sortTable
     *            the individual words contained in table
     *
     */
    private static void generateTable(String outputFile, String inputTitle,
            int numbers, Map<String, Integer> countTable,
            SortingMachine<Pair<String, Integer>> sortTable) {

        SimpleWriter out = new SimpleWriter1L(outputFile);

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
        sortTable.changeToExtractionMode();

        //HTML for sorted words
        while (sortTable.size() > 0) {
            Pair<String, Integer> sort = sortTable.removeFirst();
            out.println("<span style=\"cursor:default\" class=\"" + "f"
                    + sort.value().toString() + "\" title=\"count: "
                    + sort.value() + "\">" + sort.key() + "</span>");
        }

        //closing html out
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        // out.close();
    }

    /**
     * Counts the number of occurences of each word in the inputted text.
     *
     * @param input
     *            the text file inputted by the user
     * @param table
     *            the {@code Map} that pairs the word with its number of
     *            occurences
     */
    public static void wordCounter(SimpleReader input,
            Map<String, Integer> table) {
        //initializes seperators that may interfere with word count putting them
        //into a set
        final String seperators = " /n,.!?-@#$%^&*()_:'[]";
        Set<Character> seperatorSet = new Set1L<>();
        for (int i = 0; i < seperators.length(); i++) {
            seperatorSet.add(seperators.charAt(i));
        }
        //iterates through each individual line of the inputted text
        while (!input.atEOS()) {
            String line = input.nextLine();
            int i = 0;
            while (i < line.length()) {
                //initializes word to either a string or seperator
                String word = nextWordOrSeparator(line, i, seperatorSet);
                //if word is already a key, its value increases, if not a key
                //is created for it
                if (table.hasKey(word)) {
                    table.replaceValue(word, table.value(word) + 1);
                } else if (!seperatorSet.contains(word.charAt(0))) {
                    table.add(word, 1);
                }
                //moves position
                i += word.length();
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
     * @param separators
     *            the {@code Set} of separator characters
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
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String result = "";
        char character = text.charAt(position);
        int i = position;
        if (separators.contains(character)) {
            while (i < text.length() && separators.contains(text.charAt(i))) {
                character = text.charAt(i);
                result += character;
                i++;
            }
        } else {
            while (i < text.length() && !separators.contains(text.charAt(i))) {
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
     *            words in inputted text along with each number of occurences.
     * @param numWrds
     * @return organized {@code Queue} in alphabetical order.
     */
    private static SortingMachine<Pair<String, Integer>> sort(
            Map<String, Integer> words, int numWrds) {

        //  Map<String, Integer> n = new Map1L<String, Integer>();
        // wordCounter(inFile, n);

        Comparator<Map.Pair<String, Integer>> countComp = new Count();
        SortingMachine<Map.Pair<String, Integer>> sortNum = new SortingMachine1L<>(
                countComp);

        //inputs each word into queue
        for (Map.Pair<String, Integer> temp : words) {
            sortNum.add(temp);
        }
        Comparator<Map.Pair<String, Integer>> wordComp = new Alphabetize();
        SortingMachine<Map.Pair<String, Integer>> sortWords = new SortingMachine1L<>(
                wordComp);

        sortNum.changeToExtractionMode();
        int sz = sortNum.size();
        if (numWrds > sz) {
            for (int i = 0; i < sz; i++) {
                Map.Pair<String, Integer> temp = sortNum.removeFirst();
                sortWords.add(temp);
            }
        } else {
            for (int i = 0; i < numWrds; i++) {
                Map.Pair<String, Integer> temp = sortNum.removeFirst();
                sortWords.add(temp);
            }
        }
//        for (int i = 0; i < Math.min(numWrds, sz); i++) {
//            Map.Pair<String, Integer> temp = sortNum.removeFirst();
//            sortWords.add(temp);
//        }
        //creates new comparator and organizes in alphabetical order
        //Comparator<Pair<String, Integer>> alphabetize = new alphabetizeComp();
        // for (Map.Pair<String, Integer> x : n) {
        // sortWords.add(x);
        return sortWords;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //asks for user input file and output file name
        out.println("Input file name");
        String input = in.nextLine();
        out.println("Enter the name of an output file");
        String output = in.nextLine();
        out.println("Enter a number of words to be included");
        int wordsNum = in.nextInteger();

        //creates a map for word and its number of occurences
        //Map<Pair<String, Integer>, Integer> table = new Map1L<>();
        Map<String, Integer> table = new Map1L<>();
        SimpleReader inputFile = new SimpleReader1L(input);
        wordCounter(inputFile, table);
        SortingMachine<Pair<String, Integer>> sorted = sort(table, wordsNum);
        //calls upon method to get final result
        SimpleWriter outputFile = new SimpleWriter1L(output);

        generateTable(output, input, wordsNum, table, sorted);
        //closes streams
        in.close();
        out.close();
        inputFile.close();
        outputFile.close();
    }
//data/importance.txt
}
