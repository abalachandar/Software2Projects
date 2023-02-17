import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Adithya and Majed
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

    /**
     * Tests add when set is empty.
     */
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

    /**
     * Tests add when set is not empty.
     */
    @Test
    public final void add2() {
        Set<String> set = this.createFromArgsTest("3", "4");
        Set<String> expected = this.createFromArgsRef("2", "3", "4");
        set.add("2");
        assertEquals(expected, set);
    }

    /**
     * Tests add when set is not empty.
     */
    @Test
    public final void add3() {
        Set<String> set = this.createFromArgsTest("Ohio", "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Ohio",
                "Michigan");
        set.add("Florida");
        assertEquals(expected, set);
    }

    /**
     * Tests remove with only one value in set.
     */
    @Test
    public final void remove1() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("4");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */

        String y = s.remove("4");
        /*
         * Assert that values of variables match expectations
         */

        assertEquals("4", y);
        assertEquals(sExpected, s);
    }

    /**
     * Tests remove with multiple values in set.
     */
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

    /**
     * Tests remove with multiple values in set.
     */
    @Test
    public final void remove3() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Michigan");
        String removed = set.remove("Ohio");
        assertEquals("Ohio", removed);
        assertEquals(expected, set);
    }

    /**
     * Tests size when set is empty.
     */
    @Test
    public final void size1() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        /*
         * Call method under test
         */

        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s.size(), i);

    }

    /**
     * Tests size when set is not empty.
     */
    @Test
    public final void size2() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Ohio",
                "Michigan");
        int size1 = set.size();
        int size2 = expected.size();
        assertEquals(size1 == size2, true);
    }

    /**
     * Tests contains when value is in set.
     */
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

    /**
     * Tests contains when value is not in set.
     */
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

    /**
     * Tests contains when value is in set.
     */
    @Test
    public final void contains3() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        String individual = "Ohio";
        assertEquals(true, set.contains(individual));
    }

    /**
     * Tests contains when set is empty.
     */
    @Test
    public final void contains4() {
        Set<String> set = this.createFromArgsTest();
        String individual = "567";
        assertEquals(false, set.contains(individual));
    }

    /**
     * Tests remove any with multiple values in set.
     */
    @Test
    public final void testRemoveAny1() {
        //Initilize test and ref
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Ohio",
                "Michigan");
        //Call remove any
        String word = set.removeAny();
        //assert that the reference contains the removed
        assertEquals(false, set.contains(word));
        //Assert euqals (valueRemoved, ref.remove(valueRemoved)
        assertEquals(word, expected.remove(word));
        //Assert Equals (test, ref)
        assertEquals(set, expected);
    }

    /**
     * Tests remove any with multiple values in set.
     */
    @Test
    public final void testRemoveAny2() {
        Set<String> s = this.createFromArgsTest("1", "2", "3");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3");

        String num = s.removeAny();

        assertEquals(false, s.contains(num));
        assertEquals(sExpected.remove(num), num);
        assertEquals(sExpected, s);
    }

    /**
     * Tests remove any with only one value in set.
     */
    @Test
    public final void testRemoveAny3() {
        Set<String> s = this.createFromArgsTest("Ohio");
        Set<String> sExpected = this.createFromArgsRef("Ohio");

        String word = s.removeAny();

        assertEquals(false, s.contains(word));
        assertEquals(word, sExpected.remove(word));
        assertEquals(s, sExpected);
    }
}
