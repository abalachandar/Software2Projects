import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

    /**
     * Tests standard constructor.
     */
    @Test
    public final void testConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(nExpected, n);
    }

    /**
     * Tests integer constructor.
     */
    @Test
    public final void testConstructorInt() {
        final int five = 5;
        NaturalNumber n = this.constructorTest(five);
        NaturalNumber nExpected = this.constructorRef(five);
        assertEquals(n, nExpected);
    }

    /**
     * Tests string constructor.
     */
    @Test
    public final void testConstructorString() {
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber nExpected = this.constructorRef("5");
        assertEquals(n, nExpected);
    }

    /**
     * Tests Natural Number constructor.
     */
    @Test
    public final void testConstructorNN() {
        final int five = 5;
        NaturalNumber n = this.constructorTest(five);
        NaturalNumber nExpected = this.constructorRef(five);
        assertEquals(nExpected, n);
    }

    /**
     * test Boundary case lower end boundary.
     */
    @Test
    public final void multiplyBy10Test1() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("0");
        NaturalNumber sExpected = this.constructorRef("0");
        /*
         * Call method under test
         */

        s.multiplyBy10(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * test Boundary case higher end boundary.
     */
    @Test
    public final void multiplyBy10Test2() {
        /*
         * Set up variables
         */
        final int seven = 7;
        NaturalNumber s = this.constructorTest("214748364");
        //2147483647
        NaturalNumber sExpected = this.constructorRef("2147483647");
        /*
         * Call method under test
         */

        s.multiplyBy10(seven);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * testing Routine case.
     */
    @Test
    public final void multiplyBy10Test3() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("5");
        NaturalNumber sExpected = this.constructorRef("50");
        /*
         * Call method under test
         */

        s.multiplyBy10(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Testing routine case.
     */
    @Test
    public final void multiplyBy10Test4() {
        /*
         * Set up variables
         */
        final int eight = 8;
        NaturalNumber s = this.constructorTest("115");
        NaturalNumber sExpected = this.constructorRef("1158");
        /*
         * Call method under test
         */

        s.multiplyBy10(eight);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * test routine case.
     */
    @Test
    public final void multiplyBy10Test5() {
        /*
         * Set up variables
         */
        final int eight = 8;
        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef("8");
        /*
         * Call method under test
         */

        s.multiplyBy10(eight);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * test routine case.
     */
    @Test
    public final void multiplyBy10Test6() {
        /*
         * Set up variables
         */
        final int five = 5;
        NaturalNumber s = this.constructorTest("198");
        NaturalNumber sExpected = this.constructorRef("1985");
        /*
         * Call method under test
         */

        s.multiplyBy10(five);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * test routine case.
     */
    @Test
    public final void multiplyBy10Test7() {
        /*
         * Set up variables
         */
        final int nine = 9;
        NaturalNumber s = this.constructorTest("198");
        NaturalNumber sExpected = this.constructorRef("1989");
        /*
         * Call method under test
         */

        s.multiplyBy10(nine);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * test routine case.
     */
    @Test
    public final void multiplyBy10Test8() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef();
        /*
         * Call method under test
         */

        s.multiplyBy10(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * testing routine case.
     */
    @Test
    public final void divideBy10Test1() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("50");
        NaturalNumber sExpected = this.constructorRef("5");
        /*
         * Call method under test
         */

        s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * test Boundary case Higher end boundary.
     */
    @Test
    public final void divideBy10Test2() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("2147483647");
        NaturalNumber sExpected = this.constructorRef("214748364");
        /*
         * Call method under test
         */

        s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * testing challenge case.
     */
    @Test
    public final void divideBy10Test3() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("7");
        NaturalNumber sExpected = this.constructorRef("0");
        /*
         * Call method under test
         */

        s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * test Boundary case lower end boundary.
     */
    @Test
    public final void divideBy10Test4() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("0");
        NaturalNumber sExpected = this.constructorRef("0");
        /*
         * Call method under test
         */

        s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }
 /**
     * test routine case.
     */
    @Test
    public final void divideBy10Test5() {
        /*
         * Set up variables
         */
        final int seventysix = 76;
        NaturalNumber s = this.constructorTest(76);
        NaturalNumber sExpected = this.constructorRef("7");
        /*
         * Call method under test
         */

        int rem = s.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(rem, 6);
    }

    /**
     * test routine case.
     */
    @Test
    public final void divideBy10Test6() {
        /*
         * Set up variables
         */
        final int bignum = 4578;
        NaturalNumber s = this.constructorTest(4578);
        NaturalNumber sExpected = this.constructorRef("457");
        /*
         * Call method under test
         */

        int rem = s.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(rem, 8);
    }

    /**
     * test routine case.
     */
    @Test
    public final void divideBy10Test7() {
        /*
         * Set up variables
         */
        final int smallnum = 2;
        NaturalNumber s = this.constructorTest(2);
        NaturalNumber sExpected = this.constructorRef("0");
        /*
         * Call method under test
         */

        int rem = s.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(rem, 2);
    }

    /**
     * test routine case.
     */
    @Test
    public final void divideBy10Test8() {
        /*
         * Set up variables
         */
        final int numWithzero = 10000;
        NaturalNumber s = this.constructorTest(10000);
        NaturalNumber sExpected = this.constructorRef("1000");
        /*
         * Call method under test
         */

        int rem = s.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(rem, 0);
    }
    /**
     * test routine case of the remainder.
     */
    @Test
    public final void divideBy10TestRem1() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("5");
        final int five = 5;

        /*
         * Call method under test
         */

        int rem = s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(rem, five);
    }

    /**
     * test boundary case of the remainder.
     */
    @Test
    public final void divideBy10TestRem2() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("20");
        NaturalNumber sExpected = this.constructorRef("2");

        /*
         * Call method under test
         */

        int rem = s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(rem, 0);
    }

    /**
     * Tests isZero when Natural Number is zero.
     */
    @Test
    public final void testIsZero() {
        NaturalNumber n = this.constructorTest();
        boolean isZero = n.isZero();
        assertEquals(true, isZero);
    }

    /**
     * Tests isZero when Natural Number is not zero.
     */
    @Test
    public final void testIsZero1() {
        NaturalNumber n = this.constructorTest("734");
        boolean isZero = n.isZero();
        assertEquals(false, isZero);
    }
    /**
     * Tests isZero when Natural Number is zero.
     */
    @Test
    public final void testIsZero3() {
        NaturalNumber n = this.constructorTest("1000");
        boolean isZero = n.isZero();
        assertEquals(false, isZero);
    }

    /**
     * Tests isZero when Natural Number is zero.
     */
    @Test
    public final void testIsZero4() {
        NaturalNumber n = this.constructorTest("502");
        boolean isZero = n.isZero();
        assertEquals(false, isZero);
    }

}
