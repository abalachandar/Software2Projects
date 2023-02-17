import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size
    @Test
    public final void add1() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("3");
        /*
         * Call method under test
         */
        s.add("3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void add2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("3", "4");
        Set<String> sExpected = this.createFromArgsRef("3", "4", "5");
        /*
         * Call method under test
         */
        s.add("5");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void remove1() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("4", "5", "6", "7");
        Set<String> sExpected = this.createFromArgsRef("5", "6", "7");
        /*
         * Call method under test
         */

        String y = s.remove("4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("4", y);
    }

    @Test
    public final void remove2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("4", "5", "6", "7");
        Set<String> sExpected = this.createFromArgsRef("4", "5", "7");
        /*
         * Call method under test
         */

        String y = s.remove("6");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("6", y);
    }

    @Test
    public final void removeAny() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("4", "5", "6", "7");
        /*
         * Call method under test
         */

        String y = s.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("4", y);
        assertEquals("5", y);
        assertEquals("6", y);
        assertEquals("7", y);
    }

    @Test
    public final void length1() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("4", "5", "6", "7");
        /*
         * Call method under test
         */

        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(4, i);

    }

    @Test
    public final void contains1() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("4", "5", "6");
        /*
         * Call method under test
         */

        boolean contains = s.contains("4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, contains);
    }

    @Test
    public final void contains2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Ohio", "Florida");
        /*
         * Call method under test
         */

        boolean contains = s.contains("Texas");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, contains);
    }

    @Test
    public final void testAdd() {
        Set<String> set = this.createFromArgsTest("Ohio", "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Ohio",
                "Michigan");
        set.add("Florida");
        assertEquals(set, expected);
    }

    @Test
    public final void testRemoveNonEmpty() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Michigan");
        set.remove("Ohio");
        assertEquals(set, expected);
    }

    @Test
    public final void testRemoveAny() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        set.removeAny();

        int size = set.size();
        assertEquals(size, 2);
    }

    @Test
    public final void testContains() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        String individual = "red";
        assertEquals(set.contains(individual), false);
    }

    @Test
    public final void testSize() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Ohio",
                "Michigan");
        int size1 = set.size();
        int size2 = expected.size();
        assertEquals(size1 == size2, true);
    }
}
