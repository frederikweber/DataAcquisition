package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataTest {

	@Test
	public void testData() {
		Data data = new Data(3.3, 3.3);
		assertNotNull(data);
		assertEquals(3.3, data.getX(), 0.001);
		assertEquals(3.3, data.getY(), 0.001);
	}

}
