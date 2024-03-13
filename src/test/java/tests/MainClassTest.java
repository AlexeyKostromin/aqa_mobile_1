package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainClassTest extends MainClass{

    @Test
    void testGetLocalNumber() {
        int localNumber = this.getLocalNumber();
        Assertions.assertEquals(14, localNumber, "Expected number is 14, but was" + localNumber);
    }
}
