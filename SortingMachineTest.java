import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Adithya and Majed
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    /**
     * Test constructor.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /**
     * Test add empty.
     */
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /**
     * routin case for add.
     */
    @Test
    public final void add2() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                " red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", " red", "blue");
        m.add("blue");

        assertEquals(mExpected, m);
    }

    /**
     * routin case for add.
     */
    @Test
    public final void testAdd3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Ohio");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Texas", "Ohio");
        m.add("Texas");
        assertEquals(mExpected, m);
    }

    @Test
    public final void chnageToExtractionMode1() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue");

        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void removeFirst1() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String rem = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(rem, "green");
    }

    @Test
    public final void removeFirst2() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "red");
        String rem = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(rem, "green");
    }

    @Test
    public final void isInInsertionMode1() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                " red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                " red");

        boolean isIn = m.isInInsertionMode();

        assertEquals(true, isIn);
    }

    @Test
    public final void isInInsertionMode2() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", " red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", " red");

        boolean isIn = m.isInInsertionMode();

        assertEquals(false, isIn);
        assertEquals(mExpected, m);
    }

    @Test
    public final void order1() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                " red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", " red");

        assertEquals(mExpected, m);
    }

    @Test
    public final void size1() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        int size = m.size();

        assertEquals(0, size);
        assertEquals(mExpected, m);
    }

    @Test
    public final void size2() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                " red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", " red");
        int size = m.size();

        assertEquals(2, size);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        m.changeToExtractionMode();
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Ohio");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Ohio");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Ohio",
                "Florida", "Texas");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Ohio", "Florida", "Texas");
        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }
}
