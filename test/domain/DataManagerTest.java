package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataManagerTest {

	@Test
	public void testGetUniqueInstance() {
		assertNotNull(DataManager.getUniqueInstance());
	}

	@Test
	public void testAddData() {
		DataManager.getUniqueInstance().addData(new Data(3.3, 3.3));
		assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getX(), 0.001);
		assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getY(), 0.001);
	}

	@Test
	public void testGetDataList() {
		DataManager.getUniqueInstance().addData(new Data(3.3, 3.3));
		assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getX(), 0.001);
		assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getY(), 0.001);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testClear() {
		DataManager.getUniqueInstance().clear();
		assertNull(DataManager.getUniqueInstance().getDataList().get(0));
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testDelete() {
		DataManager.getUniqueInstance().addData(new Data(3.3, 3.3));
		assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getX(), 0.001);
		assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getY(), 0.001);
		DataManager.getUniqueInstance().delete(new Data(3.3, 3.3));
		assertNull(DataManager.getUniqueInstance().getDataList().get(0));
	}

}