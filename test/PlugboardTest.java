import main.Plugboard;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SuppressWarnings("NestedMethodCall")
public class PlugboardTest {

    // Taken from the respective .pb files in ../../plugboards/
    private final int[] pb0 = {};
    private final int[] pb1 = {25, 8};
    private final int[] pb2 = {25, 10, 22, 9, 21, 4};

    @Test
    public void testEmptyPlugboard() {
        Plugboard mypb0 = new Plugboard(pb0);
        for (char c = 'A'; c <= 'Z'; c++) {
            assertThat(mypb0.translate(c), is(c));
        }
    }

    @Test
    public void testPlugboard1() {
        Plugboard mypb1 = new Plugboard(pb1);
        for (char c = 'A'; c < 'I'; c++) {
            assertThat(mypb1.translate(c), is(c));
        }
        assertThat(mypb1.translate('I'), is('Z'));
        for (char c = 'J'; c < 'Z'; c++) {
            assertThat(mypb1.translate(c), is(c));
        }
        assertThat(mypb1.translate('Z'), is('I'));
    }

    @Test
    public void testPlugboard2() {
        Plugboard mypb1 = new Plugboard(pb2);
        for (char c = 'A'; c < 'E'; c++) {
            assertThat(mypb1.translate(c), is(c));
        }
        assertThat(mypb1.translate('E'), is('V'));

        for (char c = 'F'; c < 'J'; c++) {
            assertThat(mypb1.translate(c), is(c));
        }
        assertThat(mypb1.translate('J'), is('W'));
        assertThat(mypb1.translate('K'), is('Z'));

        for (char c = 'L'; c < 'V'; c++) {
            assertThat(mypb1.translate(c), is(c));
        }
        assertThat(mypb1.translate('V'), is('E'));
        assertThat(mypb1.translate('W'), is('J'));

        for (char c = 'X'; c < 'Z'; c++) {
            assertThat(mypb1.translate(c), is(c));
        }
        assertThat(mypb1.translate('Z'), is('K'));
    }

}
