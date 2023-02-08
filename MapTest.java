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
    public final void testRemove() {
        Map<String, String> tester = this.createFromArgsTest("Ohio", "Florida",
                "Texas", "Michigan");
        Map<String, String> expected = this.createFromArgsRef("Texas",
                "Michigan");

        tester.remove("Ohio");
        assertEquals(tester, expected);

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

}
