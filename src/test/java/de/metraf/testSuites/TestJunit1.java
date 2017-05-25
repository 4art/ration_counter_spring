package de.metraf.testSuites;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Created by metraf on 04.05.17.
 */
public class TestJunit1 {
    String message = "Artem";

    @Test
    public void testPrintMessage() {
        System.out.println("test testPrintMessage()");
        assertEquals(message, "Artem");
    }
}
