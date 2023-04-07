import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Majed and Audithya
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        //starts by dequeueing
        tokens.dequeue();
        String indentifier = tokens.dequeue();

        Reporter.assertElseFatalError(Tokenizer.isIdentifier(indentifier),
                "Instruction " + indentifier + "is invalid");
        //checks for IS block
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "IS Expected");
        //parses Block
        body.parseBlock(tokens);
        //checks of ending
        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "END Expected");
        Reporter.assertElseFatalError(tokens.dequeue().equals(indentifier),
                "Expected " + indentifier);
        return indentifier;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        //clears token if only 1 item in 
        if (tokens.front().equals(Tokenizer.END_OF_INPUT)) {
            this.clear();
        } else {
            Reporter.assertElseFatalError(tokens.dequeue().equals("PROGRAM"),
                    "PROGRAM Expected");

            Reporter.assertElseFatalError(
                    Tokenizer.isIdentifier(tokens.front()),
                    "Name must be an indentifier");
            //String dequeue
            String newName = tokens.dequeue();
            Reporter.assertElseFatalError(tokens.front().equals("IS"),
                    "must begin with IS");
            tokens.dequeue();

            Reporter.assertElseFatalError(tokens.length() > 0,
                    "Unexpected ERROR");

            // creating new map to swap from
            Map<String, Statement> newContext = this.newContext();
            while (tokens.front().equals("INSTRUCTION")) {
                Statement block = this.newBody();
                String indentifier = parseInstruction(tokens, block);
                Reporter.assertElseFatalError(!newContext.hasKey(indentifier),
                        "User defined instructions names must be a unique Identifier");
                //adds new context
                newContext.add(indentifier, block);
            }
            Reporter.assertElseFatalError(tokens.dequeue().equals("BEGIN"),
                    "BEGIN Expected");
            Statement newBody = this.newBody();
            //Parses block for new body
            newBody.parseBlock(tokens);

            Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                    "Expected END");
            Reporter.assertElseFatalError(tokens.dequeue().equals(newName),
                    newName + " Expected");

            Reporter.assertElseFatalError(
                    tokens.front().equals(Tokenizer.END_OF_INPUT),
                    "ERROR Expected");

            //puts together by swapping context, body and adding name
            this.setName(newName);
            this.swapContext(newContext);
            this.swapBody(newBody);

        }
    }
    //}

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
