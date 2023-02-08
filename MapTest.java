import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Adithya and Majed
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size
    @Test
    public final void testConstructor() {
        Map<String, String> tester = this.constructorTest();
        Map<String, String> expected = this.constructorRef();
        assertEquals(tester, expected);

    }

    @Test
    public final void testAdd() {
        Map<String, String> tester = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        Map<String, String> expected = this.createFromArgsRef("Ohio", "Florida",
                "Texas", "Michigan", "Iowa", "Alabama");

        tester.add("Iowa", "Alabama");
        assertEquals(tester, expected);
    }
    @Test
    public final void add1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef("101", "102", "103");
        Map<String, String> mExpected = this.createFromArgsRef("101", "102",
                "103", "104");
        /*
         * Call method under test
         */
        m.add("PW", "104");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void add2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef();
        Map<String, String> mExpected = this.createFromArgsRef("17");
        /*
         * Call method under test
         */
        m.add("PB", "17");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void add3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef("1005", "1234");
        Map<String, String> mExpected = this.createFromArgsRef("1005", "1234",
                "8769");
        /*
         * Call method under test
         */
        m.add("PF", "8769");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }
    @Test
    public final void testRemove() {
        Map<String, String> tester = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        Map<String, String> expected = this.createFromArgsRef("Texas",
                "Michigan");

        tester.remove("Ohio");
        assertEquals(tester, expected);

    }
  @Test
    public final void remove1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("10", "11", "12", "13");
        Map<String, String> mExpected = this.createFromArgsRef("11", "12",
                "13");
        /*
         * Call method under test
         */

        m.remove("10");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("4", m);
    }

    @Test
    public final void remove2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("879");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */

        m.remove("879");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("879", m);
    }

    @Test
    public final void remove3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("10", "11", "12", "13");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */

        m.remove("12");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("12", m);
    }
 @Test
    public final void removeAny() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("17", "50", "82",
                "147");
        Map<String, String> mExpected = this.createFromArgsRef("17", "50",
                "82");
        /*
         * Call method under test
         */

        m.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("17", m);
        assertEquals("50", m);
        assertEquals("82", m);
        assertEquals("147", m);
    }

    @Test
    public final void testRemoveAny() {
        Map<String, String> tester = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        int size1 = tester.size();
        tester.removeAny();
        int size2 = tester.size();
        assertEquals(size1, size2 + 1);
    }

    @Test
    public final void testValue() {
        Map<String, String> tester = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        String test = tester.value("Ohio");
        assertEquals(test, "Florida");
    }
 @Test
    public final void Value1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("PB");
        Map<String, String> mExpected = this.createFromArgsRef("4", "5", "6",
                "7");
        /*
         * Call method under test
         */

        m.value("PB");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(4, "PB");
        assertEquals(mExpected, m);
    }
    /**
     *
     * Test for Has-key.
     */
    @Test
    public final void testHasKey() {
        Map<String, String> tester = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        boolean test = tester.hasKey("Ohio");
        assertEquals(test, true);
    }
   @Test
    public final void hasKey2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("9","7","5","12");
        /*
         * Call method under test
         */

        boolean test = m.hasKey("10");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, false);
    }
    /**
     * Test for Size.
     */
    @Test
    public final void testSize() {
        Map<String, String> tester = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        int size = tester.size();
        int expectedSize = 2;
        assertEquals(size, expectedSize);
    }
        @Test
    public final void size1() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("17", "18", "21", "22");
        Map<String, String> sExpected = this.createFromArgsRef("17", "18", "21",
                "22");
        /*
         * Call method under test
         */

        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(4, i);
        assertEquals(sExpected, s);

    }

}
