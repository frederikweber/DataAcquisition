package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneratorTest {

    @Test
    public void testGetSinusValues() {
        Data data = Generator.getSinusValues().get(0);
        assertEquals(data.getX(), 0.0, 0.001);
        assertEquals(data.getY(), 0.0, 0.001);
    }

    @Test
    public void testGetRandomValues() {
        Data data1 = Generator.getRandomValues().get(0);
        Data data2 = Generator.getRandomValues().get(0);
        assertFalse(data1.equals(data2));
    }

}
