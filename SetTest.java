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

    //Constructor, Contains false on empty, size 0

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
        Set<String> set = this.createFromArgsTest("3", "4");
        Set<String> expected = this.createFromArgsRef("2", "3", "4");
        set.add("2");
        assertEquals(set, expected);
    }

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
        String removed = set.remove("Ohio");
        assertEquals("Ohio", removed);
        assertEquals(set, expected);
    }

    @Test
    public final void TestRemoveAnyStruct() {
        //Initilize test and ref

        //Call remove any

        //assert that the reference contains the removed
        //Assert euqals (valueRemoved, ref.remove(valueRemoved)
        //Assert Equals (test, ref)
    }

    @Test
    public final void TestRemoveAny1() {
        Set<String> s = this.createFromArgsTest("1", "2", "3");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3");

        String num = s.removeAny();
        boolean test = sExpected.contains(num);
        assertEquals(true, test);
        sExpected.remove("1");
        assertEquals(sExpected, s);
    }

    @Test
    public final void TestRemoveAny2() {
        Set<String> s = this.createFromArgsTest("Ohio", "Florida", "Texas");
        Set<String> sExpected = this.createFromArgsRef("Ohio", "Florida",
                "Texas");

        String word = s.removeAny();
        boolean test = sExpected.contains(word);
        assertEquals(true, test);
        sExpected.remove("Florida");
        assertEquals(sExpected, s);
    }

    @Test
    public final void TestRemoveAny3() {
        Set<String> s = this.createFromArgsTest("Ohio");
        Set<String> sExpected = this.createFromArgsRef("Ohio");

        String word = s.removeAny();
        boolean test = sExpected.contains(word);
        assertEquals(true, test);
        sExpected.remove("Ohio");
        assertEquals(sExpected, s);
    }

    @Test
    public final void contains4() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        String individual = "red";
        assertEquals(set.contains(individual), false);
    }

    @Test
    public final void contains5() {
        Set<String> set = this.createFromArgsTest();
        String individual = "567";
        assertEquals(set.contains(individual), false);
    }

    @Test
    public final void size1() {
        Set<String> set = this.createFromArgsTest("Florida", "Ohio",
                "Michigan");
        Set<String> expected = this.createFromArgsRef("Florida", "Ohio",
                "Michigan");
        int size1 = set.size();
        int size2 = expected.size();
        assertEquals(size1 == size2, true);
    }

    @Test
    public final void size2() {
        Set<String> set = this.createFromArgsTest();
        Set<String> expected = this.createFromArgsRef();
        int size1 = set.size();
        int size2 = expected.size();
        assertEquals(size1 == size2, true);
    }
}
