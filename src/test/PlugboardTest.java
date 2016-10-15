package test;

import main.Plugboard;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PlugboardTest {

    // Taken from the respective .pb files in ../../plugboards/
    private int[] pb0 = {};
    private int[] pb1 = {25,8};
    private int[] pb2 = {25,10,22,9,21,4};

    @Test
    public void testEmptyPlugboard() {
        Plugboard mypb0 = new Plugboard(pb0);
        for (char c = 'A'; c <= 'Z'; c++) {
            assertThat(mypb0.translate(c), is(c));

        }
    }

}
