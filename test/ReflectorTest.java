import main.Reflector;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class ReflectorTest {

	@Test //Tests that mapping is as expected
	public void testReflector() {
		assertThat(Reflector.reflect('N'), is('A'));
		assertThat(Reflector.reflect('A'), is('N'));
		assertThat(Reflector.reflect('B'), is('O'));
		assertThat(Reflector.reflect('M'), is('Z'));
	}
	
	@Test 	//Tests that out of bound characters will throw exceptions, 
			//@ and [ are the edge cases
	public void reflectThrowsIndexOutOfBoundsException() {
	  boolean thrown = false;

	  try {
	    Reflector.reflect('@');
	  } catch (IndexOutOfBoundsException e) {
	    thrown = true;
	  }

	  assertTrue(thrown);
	  
	  thrown = false;
	  try {
		    Reflector.reflect('[');
		  } catch (IndexOutOfBoundsException e) {
		    thrown = true;
		  }

		  assertTrue(thrown);
	}
	
}