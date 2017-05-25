package de.metraf.testSuites;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by metraf on 04.05.17.
 */
public class TestJunit2 /*extends TestCase*/{
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Before
    public void setUp(){
        setMessage("Artem");
    }

    @Test
    public void checkStrings(){
//        System.out.println(this.getName());
        assertEquals("Artem", message);
    }
}
