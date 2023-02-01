import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Majed and Adithya
 *
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        //Provides a default value for this.rep.
        this.rep = "";
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {
        //Creates new string
        this.createNewRep();

    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";

        // this.rep stores int i in string form
        if (i == 0) {
            this.createNewRep();
        } else {
            this.rep = Integer.toString(i);
        }

    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";

        // this.rep is assigned the value stored in String s.
        if (s.equals("0")) {
            this.createNewRep();
        } else {
            this.rep = s;
        }

    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";

        // this.rep stores the value of NaturalNumber in String form.
        if (n.isZero()) {
            this.rep = ("");
        } else {
            this.rep = n.toString();
        }

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        //makes sure there is at least 1 digit and k is not 0 to be added on
        if (this.rep.length() > 0 || (k != 0)) {
            //concatenates k to the end of this.rep.
            this.rep = this.rep.concat(Integer.toString(k));
        }
    }

    @Override
    public final int divideBy10() {

        // initializes remainder to 0
        int rem = 0;
        // if statement checks whether length is greatert than 0. If not, 0
        //divided by 0 is still 0 so statement will automatically return 0.
        if (this.rep.length() != 0) {
            //Removes the final character of the string which is the ones place
            //and the remainder when divided by 10. Remainder is then parsed to
            //an int and returned.
            String digitChar = Character
                    .toString(this.rep.charAt(this.rep.length() - 1));
            rem = Integer.parseInt(digitChar);
            //Cuts off the final character of the string, storing whats left
            // into this.rep.
            this.rep = this.rep.substring(0, this.rep.length() - 1);
        }
        return rem;
    }

    @Override
    public final boolean isZero() {
        //only value that is equal to 0 is the empty string
        return this.rep.equals("");
    }

}
