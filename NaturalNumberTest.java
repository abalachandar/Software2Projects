import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero
    /*
     * Tests standard constructor 
     */
    @Test
    public final void testConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(n, nExpected);
    }
    
    /*
     * Tests integer constructor
     */
    @Test
    public final void testConstructorInt() {
        NaturalNumber n = this.constructorTest(5);
        assertEquals(5, n.toInt());
    }
    
    /*
     * Tests string constructor
     */
    @Test
    public final void testConstructorString() {
        NaturalNumber n = this.constructorTest("n");
        assertEquals("n", n.toString());
    }
    
    /*
     * Tests Natural Number constructor
     */
    @Test
    public final void testConstructorNN() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef(5);
        assertEquals(n, nExpected);
    }
    /*
     * Tests isZero when Natural Number is zero
     */
    @Test
    public final void testIsZero() {
        NaturalNumber n = this.constructorTest(0);
        boolean isZero = n.isZero();
        assertEquals(isZero, true);
    }
    
    /*
     * Tests isZero when Natural Number is not zero
     */
    @Test
    public final void testIsZero1() {
        NaturalNumber n = this.constructorTest(734);
        boolean isZero = n.isZero();
        assertEquals(isZero, false);
    }
        /*
    * test Boundary case lower end boundary
    */
    @Test
    public final void multiplyBy10Test1() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(0);
        NaturalNumber sExpected = this.constructorRef(0);
        /*
         * Call method under test
         */

        s.multiplyBy10(0);;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(0, 0);
}
    /*
     * test Boundary case higher end boundary
     */
    @Test
    public final void multiplyBy10Test2() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(2147483647);
        //2147483647
        NaturalNumber sExpected = this.constructorRef();
        /*
         * Call method under test
         */

        s.multiplyBy10(0);;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("", 0);
}
    /*
     * testing Routine case 
     */
    @Test
    public final void multiplyBy10Test3() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(5);
        NaturalNumber sExpected = this.constructorRef(50);
        /*
         * Call method under test
         */

        s.multiplyBy10(50);;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(5, 50);
}
    /*
     * Testing challenge case
     */
    @Test
    public final void multiplyBy10Test4() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(115);
        NaturalNumber sExpected = this.constructorRef(1150);
        /*
         * Call method under test
         */

        s.multiplyBy10(1150);;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(115, 1150);
}
    /*
     * testing routine case
     */
    @Test
    public final void divideBy10Test1() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(50);
        NaturalNumber sExpected = this.constructorRef(5);
        /*
         * Call method under test
         */

        s.divideBy10();;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(50, 5);
}
    /*
     * test Boundary case Higher end boundary
     */
    @Test
    public final void divideBy10Test2() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(2147483647);
        NaturalNumber sExpected = this.constructorRef(214748364);
        /*
         * Call method under test
         */

        s.divideBy10();;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(2147483647, 214748364);
}
    /*
     * testing challenge case
     */
    @Test
    public final void divideBy10Test3() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(7);
        NaturalNumber sExpected = this.constructorRef(0);
        /*
         * Call method under test
         */

        s.divideBy10();;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(7, 0);
}
    /*
     * test Boundary case lower end boundary
     */
    @Test
    public final void divideBy10Test4() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(0);
        NaturalNumber sExpected = this.constructorRef(0);
        /*
         * Call method under test
         */

        s.divideBy10();;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(0, 0);
}
    /*
     * test routine case of the remainder
     */
    @Test
    public final void divideBy10TestRem1() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(5);
        NaturalNumber sExpected = this.constructorRef(0);
       
        /*
         * Call method under test
         */
        
        int rem = s.divideBy10();;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(rem, 5);
}
    /*
     * test boundary case of the remainder
     */
    @Test
    public final void divideBy10TestRem2() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(20);
        NaturalNumber sExpected = this.constructorRef(2);
       
        /*
         * Call method under test
         */
        
        int rem = s.divideBy10();;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(rem, 2);
    }
}
