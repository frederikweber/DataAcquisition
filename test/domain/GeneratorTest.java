package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneratorTest {

	@Test
	public void testGetUniqueInstance() {
		Generator generator = Generator.getUniqueInstance();
		assertNotNull(generator);
	}

	@Test
	public void testGetSinusValues() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRandomValues() {
		fail("Not yet implemented");
	}

}
