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

    /**
     * Tests Constructor.
     */
    @Test
    public final void testConstructor() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> expected = this.constructorRef();
        assertEquals(m, expected);

    }

    /**
     * Tests add routine case.
     */
    @Test
    public final void add1() {
        Map<String, String> m = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        /*
         * Set up variables
         */
        Map<String, String> expected = this.createFromArgsRef("Ohio", "Florida",
                "Texas", "Michigan", "Iowa", "Alabama");
        /*
         * Call method under test
         */
        m.add("Iowa", "Alabama");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, m);
    }

    /**
     * Tests add routine case.
     */
    @Test
    public final void add2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef("101", "102", "103",
                "104");
        Map<String, String> expected = this.createFromArgsRef("101", "102",
                "103", "104", "105", "106");
        /*
         * Call method under test
         */
        m.add("105", "106");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, m);
    }

    /**
     * Tests add challenge case.
     */
    @Test
    public final void add3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef();
        Map<String, String> mExpected = this.createFromArgsRef("17", "42");
        /*
         * Call method under test
         */
        m.add("17", "42");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests add routine case.
     */
    @Test
    public final void add4() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef("PB", "1234");
        Map<String, String> mExpected = this.createFromArgsRef("PB", "1234",
                "PW", "8769");
        /*
         * Call method under test
         */
        m.add("PW", "8769");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests add challenge case.
     */
    @Test
    public final void add5() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef("Ohio", "Florida",
                "Texas", "Michigan");
        Map<String, String> expected = this.createFromArgsRef("Ohio", "Florida",
                "Texas", "Michigan", "Iowa", "");
        /*
         * Call method under test
         */
        m.add("Iowa", "");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, m);
    }

    /**
     * Tests add challenge case.
     */
    @Test
    public final void add6() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsRef("Ohio", "Florida",
                "Texas", "Michigan");
        Map<String, String> expected = this.createFromArgsRef("Ohio", "Florida",
                "Texas", "Michigan", "", "Alabama");
        /*
         * Call method under test
         */
        m.add("", "Alabama");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, m);
    }

    /**
     * Tests remove routine case.
     */
    @Test
    public final void remove4() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        Map<String, String> expected = this.createFromArgsRef("Texas",
                "Michigan");
        /*
         * Call method under test
         */
        m.remove("Ohio");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, expected);

    }

    /**
     * Tests remove routine case.
     */
    @Test
    public final void remove1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("10", "11", "12", "13");
        Map<String, String> mExpected = this.createFromArgsRef("12", "13");
        /*
         * Call method under test
         */
        m.remove("10");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests remove challenge case.
     */
    @Test
    public final void remove2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("PS", "879");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */

        m.remove("PS");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests remove routine case.
     */
    @Test
    public final void remove3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("10", "11", "12", "13",
                "14", "15");
        Map<String, String> mExpected = this.createFromArgsRef("10", "11", "14",
                "15");
        /*
         * Call method under test
         */

        m.remove("12");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests remove challenge case.
     */
    @Test
    public final void remove5() {
        Map<String, String> m = this.createFromArgsTest("Ohio", "Florida", "",
                "Michigan");
        Map<String, String> expected = this.createFromArgsRef("Ohio",
                "Florida");

        m.remove("");
        assertEquals(m, expected);

    }

    /**
     * Tests removeAny routine case.
     */
    @Test
    public final void removeAny1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("17", "50", "82",
                "147");
        /*
         * Call method under test
         */

        m.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        int msize = m.size();
        int expectedSize = m.size();
        assertEquals(msize, expectedSize);
    }

    /**
     * Tests removeAny routine case.
     */
    @Test
    public final void removeAny2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        /*
         * Call method under test
         */
        m.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        int msize = m.size();
        int expectedSize = m.size();
        assertEquals(msize, expectedSize);

    }

    /**
     * Tests removeAny challenge case.
     */
    @Test
    public final void removeAny3() {
        Map<String, String> m = this.createFromArgsTest("Ohio", "Florida");
        m.removeAny();
        int msize = m.size();
        int expectedSize = m.size();
        assertEquals(msize, expectedSize);

    }

    /**
     * Tests value routine case.
     */
    @Test
    public final void value1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        /*
         * Call method under test
         */
        String test = m.value("Ohio");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, "Florida");
    }

    /**
     * Tests value challenge case.
     */
    @Test
    public final void value2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("PB", "");
        /*
         * Call method under test
         */

        String test = m.value("PB");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, "");
    }

    /**
     * Tests value routine case.
     */
    @Test
    public final void value3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("PB", "1256", "PT",
                "12500", "PW", "3987");
        /*
         * Call method under test
         */

        String test = m.value("PT");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, "12500");
    }

    /**
     * Tests value challenge case.
     */
    @Test
    public final void value4() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("", "112");
        /*
         * Call method under test
         */

        String test = m.value("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, "112");
    }

    /**
     * Test for Has-key routine case.
     */
    @Test
    public final void hasKey1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        /*
         * Call method under test
         */
        boolean test = m.hasKey("Ohio");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, true);
    }

    /**
     * Test for Has-key routine case.
     */
    @Test
    public final void hasKey2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("9", "7", "5", "12");
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
     * Test for Has-key routine case.
     */
    @Test
    public final void hasKey3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("9", "7", "5", "12",
                "152", "63");
        /*
         * Call method under test
         */

        boolean test = m.hasKey("152");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, true);
    }

    /**
     * Test for Has-key routine case.
     */
    @Test
    public final void hasKey4() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("9", "7", "5", "12",
                "152", "63", "201", "447");
        /*
         * Call method under test
         */

        boolean test = m.hasKey("200");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, false);
    }

    /**
     * Test for Has-key challenge case.
     */
    @Test
    public final void hasKey5() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("", "7", "5", "12",
                "152", "63", "201", "447");
        /*
         * Call method under test
         */

        boolean test = m.hasKey("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, true);
    }

    /**
     * Test for Size challenge case .
     */
    @Test
    public final void size1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        int sExpected = 0;
        /*
         * Call method under test
         */

        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, sExpected);
    }

    /**
     * Test for Size routine case .
     */

    @Test
    public final void size2() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("17", "18", "21", "22",
                "78", "67", "98", "21", "512", "362");
        /*
         * Call method under test
         */

        int size = s.size();
        final int sExpected = 5;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, sExpected);

    }

    /**
     * Test for Size routine case .
     */
    @Test
    public final void size3() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("17", "18");
        /*
         * Call method under test
         */

        int size = s.size();
        int sExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, sExpected);
    }
}
