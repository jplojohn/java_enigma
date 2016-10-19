import main.Rotor;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
public class RotorTest {

    // These arrays correspond to the .rot files in the ../../rotors/ directory.
    private final int[] rotor1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 0};
    //private final int[] rotor2 = {1,0,3,2,5,4,7,6,9,8,11,10,13,12,15,14,17,16,19,18,21,20,23,22,25,24};
    //private final int[] rotor3 = {21,20,2,22,18,3,1,15,19,25,14,8,7,0,16,11,17,9,23,12,24,5,6,13,10,4};
    //private final int[] rotor4 = {18,12,11,16,14,8,20,23,0,6,3,24,22,13,17,10,25,4,2,21,9,15,19,5,7,1};
    private final int[] rotor5 = {3, 23, 25, 1, 12, 14, 8, 16, 9, 20, 5, 4, 17, 21, 6, 18, 2, 11, 22, 24, 13, 19, 0, 10, 7, 15};
    //private final int[] rotor6 = {4,13,5,8,3,18,2,0,19,14,11,6,25,12,22,10,9,16,24,1,15,7,17,23,20,21};
    //private final int[] rotor7 = {15,5,7,20,3,22,24,17,1,14,0,16,21,12,13,19,4,2,9,6,23,18,25,10,11,8};


    @Test
    public void basicForwardRotorTest() {
        Rotor myRotor1 = new Rotor(rotor1, 0, 1);
        assertThat(myRotor1.translateForwards('A'), is('B'));
        assertThat(myRotor1.translateForwards('D'), is('E'));
        assertThat(myRotor1.translateForwards('Z'), is('A'));
    }

    @Test
    public void basicBackwardRotorTest() {
        Rotor myRotor1 = new Rotor(rotor1, 0, 0);
        assertThat(myRotor1.translateBackwards('A'), is('Z'));
        assertThat(myRotor1.translateBackwards('A'), is('Y'));
        assertThat(myRotor1.translateBackwards('F'), is('C'));
        assertThat(myRotor1.translateBackwards('D'), is('Z'));
    }

    @Test
    public void joinedUpRotor1() {
        Rotor myRotor1 = new Rotor(rotor1, 0, 0);
        assertThat(myRotor1.translateForwards('A'), is('B'));
        assertThat(myRotor1.translateForwards('A'), is('B'));
        assertThat(myRotor1.translateBackwards('B'), is('A'));
        assertThat(myRotor1.translateForwards('A'), is('C'));
        assertThat(myRotor1.translateBackwards('B'), is('Z'));
    }

    @Test
    public void basicForwardsRotor5Test() {
        Rotor myRotor5 = new Rotor(rotor5, 0, 0);
        assertThat(myRotor5.translateForwards('A'), is('D'));
        assertThat(myRotor5.translateForwards('L'), is('E'));
        assertThat(myRotor5.translateForwards('Z'), is('P'));
        assertThat(myRotor5.translateForwards('B'), is('X'));
    }

    @Test
    public void basicBackwardsRotor5Test() {
        Rotor myRotor5 = new Rotor(rotor5, 0, 0);
        assertThat(myRotor5.translateBackwards('D'), is('A'));
        assertThat(myRotor5.translateBackwards('D'), is('Z'));
        assertThat(myRotor5.translateBackwards('D'), is('Y'));
    }
// {3,23,25,1,12,14,8,16,9,20,5,4,17,21,6,18,2,11,22,24,13,19,0,10,7,15};

    @Test
    public void rotateAroundTest1stRotor() {
        Rotor myRotor1 = new Rotor(rotor1, 0, 0);
        assertThat(myRotor1.translateBackwards('B'), is('A'));
        for (int i = 0; i < (23); i++) {
            myRotor1.translateBackwards('B');
        }
        assertThat(myRotor1.translateBackwards('B'), is('C'));
        assertThat(myRotor1.translateBackwards('B'), is('B'));
        assertThat(myRotor1.translateBackwards('B'), is('A'));
        assertThat(myRotor1.translateBackwards('B'), is('Z'));
    }

    @Test
    public void rotateAroundTest2ndRotor() {
        Rotor myRotor1 = new Rotor(rotor1, 0, 1);
        for (int i = 0; (i < (Math.pow(26,1))); i++) {
            assertThat(myRotor1.translateBackwards('B'),is('A'));
        }
        assertThat(myRotor1.translateBackwards('B'), is('Z'));
    }
    @Test
    public void rotateAroundTest3rdRotor() {
        Rotor myRotor1 = new Rotor(rotor1, 0, 2);
        for (int i = 0; (i < (Math.pow(26,2))); i++) {
            assertThat(myRotor1.translateBackwards('B'),is('A'));
        }
        assertThat(myRotor1.translateBackwards('B'), is('Z'));
    }

	 /*

	  @Test
	  public void initialiseEmpty() {
	    RecentlyUsedList<Integer> testList = new RecentlyUsedList<Integer>();
	    assertThat(testList.empty(), is(true));
	    int one = 1;
	    testList = testList.add(one);
	    assertThat(testList.empty(), is(false));
	  }

	  @Test
	  public void addTest() {
	    RecentlyUsedList<Integer> testList = new RecentlyUsedList<Integer>();
	    testList = testList.add(1);
	    assertThat(testList.contains(1), is(true));
	    testList = testList.add(2);
	    testList = testList.add(3);
	    assertThat(testList.contains(2), is(true));
	    assertThat(testList.contains(3), is(true));
	    assertThat(testList.contains(4), is(false));
	    testList = testList.add(1);
	    assertThat(testList.getHead(), is(1));
	    assertThat(testList.getNext().contains(1), is(false));
	  }
	  @Test
	  public void sizeTest() {
	    RecentlyUsedList<Integer> testList = generateTestIntList();
	    assertThat(testList.size(), is(4));
	    int[] expectedTestList = {4, 3, 2, 1};
	    final int constSIZE = testList.size();
	    for (int i = 0; i < constSIZE; i++) {
	      assertThat(expectedTestList[i], is(testList.getHead()));
	      assertThat(expectedTestList.length, is(constSIZE));
	      testList = testList.getNext();
	    }
	  }

	  @Test
	  public void deleteTest() {
	    RecentlyUsedList<Integer> testList = generateTestIntList();
	    testList.delete(3);
	    assertThat(testList.contains(3), is(false));
	    testList.delete(4);
	    assertThat(testList.contains(4), is(false));
	    assertThat(testList.contains(2), is(true));
	    assertThat(testList.contains(1), is(true));
	    RecentlyUsedList<Integer> emptyList = new RecentlyUsedList<Integer>();
	    emptyList.delete(3);
	  }

	  @Test
	  public void emptyTest() {
	    RecentlyUsedList<Integer> testList = generateTestIntList();
	    RecentlyUsedList<Object> emptyList = new RecentlyUsedList<Object>();
	    assertThat(testList.empty(), is(false));
	    assertThat(emptyList.empty(), is(true));
	  }

	  @Test
	  public void retrievalTest() {
	    RecentlyUsedList<Object> emptyList = new RecentlyUsedList<Object>();
	    assertArrayEquals(new Object[] {}, emptyList.toArray());
	    RecentlyUsedList<Integer> singletonList = new RecentlyUsedList<Integer>();
	    singletonList = singletonList.add(1);
	    assertArrayEquals(new Integer[] {1}, singletonList.toArray());
	    RecentlyUsedList<Integer> testList = generateTestIntList();
	    RecentlyUsedList<Integer> otherList = generateOtherTestIntList();
	    assertArrayEquals(new Integer[] {4, 3, 2, 1}, testList.toArray());
	    assertArrayEquals(new Integer[] {6, 5, 4, 3}, otherList.toArray());
	  } */
}