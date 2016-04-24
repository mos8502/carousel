package hu.nemi.carousel;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private static final String[] PAGES = { "A", "B", "C"};
    private static final String[] EXPECTED = { "C", "A", "B", "C", "A", "B", "C", "A" };

    @Test
    public void foo() throws Exception {
        String[] virtualPages = new String[PAGES.length * 2 + 2];
        for(int i = 0; i < virtualPages.length; ++i) {
            virtualPages[i] = PAGES[(i+PAGES.length - 1) % PAGES.length];
        }

        System.out.println(toString(virtualPages));
        assertTrue(toString(virtualPages), Arrays.equals(EXPECTED, virtualPages));
    }

    private String toString(String[] stringArray) {
        StringBuilder sb = new StringBuilder().append("[");
        for(int i = 0; stringArray != null && i < stringArray.length; ++i) {
            if(i > 0) {
                sb.append(", ");
            }
            sb.append(stringArray[i]);

        }
        return sb.append("]").toString();
    }
}