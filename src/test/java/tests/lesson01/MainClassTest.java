package tests.lesson01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClassTest extends MainClass {

    @Test
    void testGetLocalNumber() {
        final int expectedInt = 14;
        int localNumber = this.getLocalNumber();
        assertEquals(expectedInt, localNumber, "Expected number is " + expectedInt + ", but was " + localNumber);
    }

    @Test
    void testGetClassNumber() {
        final int expectedInt = 45;
        int classNumber = this.getClassNumber();
        assertTrue(classNumber > expectedInt, "classNumber " + classNumber + " is not greater than " + expectedInt);
    }

    @Test
    void testGetClassString() {
        String classString = this.getClassString();
        assertTrue(classString.contains("hello") || classString.contains("Hello"),
                "String should contain 'hello' or 'Hello' but was " + classString);
    }

}






