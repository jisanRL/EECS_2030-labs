package eecs2030.lab3;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Direction2Test {

	@Rule
    public Timeout globalTimeout = Timeout.seconds(1);
	
	@Test
	public void test01_setAngle() {
		Direction2 d = new Direction2();
		d.setAngle(0.0);
		assertEquals("setAngle(0.0) failed\n",
				0.0, d.getAngle(), Math.ulp(0.0));
	}
	
	@Test
	public void test02_setAngle() {
		Direction2 d = new Direction2();
		d.setAngle(90.1);
		assertEquals("setAngle(90.1) failed\n",
				90.1, d.getAngle(), Math.ulp(90.1));
	}
	
	@Test
	public void test03_setAngle() {
		Direction2 d = new Direction2();
		d.setAngle(360.0);
		assertEquals("setAngle(360.0) failed\n",
				0.0, d.getAngle(), Math.ulp(0.0));
	}
	
	@Test
	public void test04_setAngle() {
		Direction2 d = new Direction2();
		d.setAngle(-100.0);
		assertEquals("setAngle(-100.0) failed\n",
				260.0, d.getAngle(), Math.ulp(260.0));
	}
	
	@Test
	public void test05_setAngle() {
		Direction2 d = new Direction2();
		d.setAngle(360.0 * 100 + 359.9);
		assertEquals("setAngle(360.0 * 100 + 359.9) failed\n",
				359.9, d.getAngle(), Math.ulp(360));
	}
	
	@Test
	public void test06_setAngle() {
		Direction2 d = new Direction2();
		d.setAngle(-360.0 * 100 - 359.9);
		assertEquals("setAngle(360.0 * 100 + 359.9) failed\n",
				0.1, d.getAngle(), Math.ulp(1));
	}
	
	@Test
	public void test07_setAngle() {
		Direction2 d = new Direction2();
		d.setAngle(360.0 * 1e12 + 1.0);
		assertEquals("setAngle(360.0 * 1e16 + 1.0) failed\n",
				1.0, d.getAngle(), Math.ulp(1.0));
	}

	@Test
	public void test08_getX() {
		Direction2 d = new Direction2();
		assertEquals("getX() failed for direction of 0 degrees\n",
				1.0, d.getX(), Math.ulp(1));
	}
	
	@Test
	public void test09_getX() {
		Direction2 d = new Direction2();
		d.setAngle(90.0);
		assertEquals("getX() failed for direction of 90 degrees\n",
				0.0, d.getX(), Math.ulp(1));
	}
	
	@Test
	public void test10_getX() {
		Direction2 d = new Direction2();
		d.setAngle(135.0);
		assertEquals("getX() failed for direction of 135 degrees\n",
				-1.0 / Math.sqrt(2), d.getX(), Math.ulp(1));
	}
	
	@Test
	public void test11_getX() {
		Direction2 d = new Direction2();
		d.setAngle(225.0);
		assertEquals("getX() failed for direction of 225 degrees\n",
				-1.0 / Math.sqrt(2), d.getX(), Math.ulp(1));
	}
	
	@Test
	public void test12_getX() {
		Direction2 d = new Direction2();
		d.setAngle(315.0);
		assertEquals("getX() failed for direction of 315 degrees\n",
				1.0 / Math.sqrt(2), d.getX(), Math.ulp(1));
	}
	
	@Test
	public void test13_getY() {
		Direction2 d = new Direction2();
		assertEquals("getY() failed for direction of 0 degrees\n",
				0.0, d.getY(), Math.ulp(1));
	}
	
	@Test
	public void test14_getY() {
		Direction2 d = new Direction2();
		d.setAngle(90.0);
		assertEquals("getY() failed for direction of 90 degrees\n",
				1.0, d.getY(), Math.ulp(1));
	}
	
	@Test
	public void test15_getY() {
		Direction2 d = new Direction2();
		d.setAngle(135.0);
		assertEquals("getY() failed for direction of 135 degrees\n",
				1.0 / Math.sqrt(2), d.getY(), Math.ulp(1));
	}
	
	@Test
	public void test16_getY() {
		Direction2 d = new Direction2();
		d.setAngle(225.0);
		assertEquals("getY() failed for direction of 225 degrees\n",
				-1.0 / Math.sqrt(2), d.getY(), Math.ulp(1));
	}
	
	@Test
	public void test17_getY() {
		Direction2 d = new Direction2();
		d.setAngle(315.0);
		assertEquals("getY() failed for direction of 315 degrees\n",
				-1.0 / Math.sqrt(2), d.getY(), Math.ulp(1));
	}
	
	@Test
	public void test18_turn() {
		Direction2 d = new Direction2();
		double delta = 0.0;
		while (delta < 0.5) {
			delta = Math.random();
		}
		double exp = 0.0;
		for (int i = 1; i < 1000; i++) {
			exp += delta;
			if (exp >= 360.0) {
				exp -= 360.0;
			}
			d.turn(delta);
			assertEquals(exp, d.getAngle(), Math.ulp(2 * exp));
		}
	}
	
	@Test
	public void test19_turn() {
		Direction2 d = new Direction2();
		double delta = 0.0;
		while (delta < 0.5) {
			delta = Math.random();
		}
		double exp = 0.0;
		for (int i = 1; i < 200; i++) {
			exp -= delta;
			if (exp < 0.0) {
				exp += 360.0;
			}
			d.turn(-delta);
			assertEquals(exp, d.getAngle(), Math.ulp(2 * exp));
		}
	}
	
	@Test
	public void test20_equals() {
		Direction2 d = new Direction2();
		assertTrue("d.equals(d) failed, returned false\n",
				d.equals(d));
	}
	
	@Test
	public void test21_equals() {
		Direction2 d = new Direction2();
		assertFalse("d.equals(null) failed, returned true\n",
				d.equals(null));
	}
	
	@Test
	public void test22_equals() {
		Direction2 d = new Direction2();
		assertFalse("d.equals(\"0.0\") failed, returned true\n",
				d.equals("0.0"));
	}
	
	@Test
	public void test23_equals() {
		Direction2 d = new Direction2();
		Direction2 d2 = new Direction2();
		assertTrue("d.equals(d2) failed, returned false\n",
				d.equals(d2));
	}
	
	@Test
	public void test24_equals() {
		Direction2 d = new Direction2();
		Direction2 d2 = new Direction2();
		d.setAngle(32.0);
		d2.setAngle(32.0);
		assertTrue("d.equals(d2) failed, returned false\n",
				d.equals(d2));
	}
	
	@Test
	public void test25_equals() {
		Direction2 d = new Direction2();
		Direction2 d2 = new Direction2();
		d.setAngle(32.0);
		assertFalse("d.equals(d2) failed, returned true\n",
				d.equals(d2));
	}
	
	@Test
	public void test26_toString() {
		Direction2 d = new Direction2();
		assertEquals("0.0 deg", d.toString());
	}
	
	@Test
	public void test27_toString() {
		Direction2 d = new Direction2();
		d.setAngle(99.99);
		assertEquals("99.99 deg", d.toString());
	}
}
