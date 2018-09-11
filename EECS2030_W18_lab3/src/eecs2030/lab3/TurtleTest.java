package eecs2030.lab3;

import static org.junit.Assert.*;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TurtleTest {

    @Test
    public void test00_checkNonStaticFields() {
        try {
            String[] types = { "eecs2030.lab3.Direction2", "eecs2030.lab3.Pen", "eecs2030.lab3.Point2" };
            Class<?> clazz = Class.forName("eecs2030.lab3.Turtle");
            int number = 0;
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (Arrays.binarySearch(types, field.getType().getName()) < 0) {
                        fail("non-static attribute: new one of type " + field.getType().getName());
                    }
                    number++;
                }
            }
            assertEquals("non-static attributes: there are not three in your class", 3, number);

        } catch (ClassNotFoundException e) {
            fail("Class eecs2030.lab3.Turtle cannot be found");
        }
    }

    @Test
    public void test01_checkStaticFields() {
        try {
            Class<?> clazz = Class.forName("eecs2030.lab3.Turtle");
            int number = 0;
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    fail("Unexpected static field");
                }
            }
        } catch (ClassNotFoundException e) {
            fail("Class lab.turtle.Turtle cannot be found");
        }
    }

    @Test
    public void test02_noArgumentCtor() {
        Turtle t = new Turtle();
        assertTrue("default ctor: position is not (0.5, 0.5)", 
        		t.getPosition().equals(new Point2(0.5, 0.5)));
        assertTrue("default ctor: angle is not 0.0", 
        		t.getDirection().getAngle() == 0.0);
        assertTrue("default ctor: color is not black", 
        		t.getPen().getColor().equals(Color.BLACK));
    }


    @Test
    public void test03_ctor() {
        Point2 pos = new Point2(0.1, 0.1);
        double ang = -64.0;
        Color c = Color.BLUE;
        Turtle u = new Turtle(0.1, 0.1, ang, c);
        assertTrue("ctor: position is incorrect", 
        		u.getPosition().equals(pos));
        assertTrue("ctor: angle is incorrect", 
        		u.getDirection().getAngle() == 360.0 + ang);
        assertTrue("ctor: pen color is incorrect", 
        		u.getPen().getColor().equals(c));

        // illegal starting positions
        final double radius = 1.000001;
        for (int i = 0; i < 360; i++) {
            double x = radius * Math.cos(Math.toRadians(i)) + 0.5;
            double y = radius * Math.sin(Math.toRadians(i)) + 0.5;
            pos = new Point2(x, y);
            try {
                new Turtle(x, y, ang, c);
                fail(pos + " is an illegal starting position");
            } catch (IllegalArgumentException ex) {
            }
        }
    }


    @Test
    public void test04_walk() {
        Turtle t = new Turtle();
        t.walk(0.2);
        Point2 expected = new Point2(0.7, 0.5);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test
    public void test05_walk() {
        Turtle t = new Turtle(0.5, 0.5, 90, Color.BLACK);
        t.walk(0.2);
        Point2 expected = new Point2(0.5, 0.7);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test
    public void test06_walk() {
        Turtle t = new Turtle(0.5, 0.5, 180, Color.RED);
        t.walk(0.2);
        Point2 expected = new Point2(0.3, 0.5);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test
    public void test07_walk() {
        Turtle t = new Turtle(0.5, 0.5, 270, Color.GREEN);
        t.walk(0.2);
        Point2 expected = new Point2(0.5, 0.3);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test
    public void test08_walk() {
        Turtle t = new Turtle(0.2, 0.3, 45, Color.BLACK);
        t.walk(Math.hypot(0.4, 0.4));
        Point2 expected = new Point2(0.6, 0.7);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test
    public void test09_walk() {
        Turtle t = new Turtle(0.2, 0.3, 135, Color.BLACK);
        t.walk(Math.hypot(0.1, 0.1));
        Point2 expected = new Point2(0.1, 0.4);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test
    public void test10_walk() {
        Turtle t = new Turtle(0.2, 0.3, 225, Color.BLACK);
        t.walk(Math.hypot(0.1, 0.1));
        Point2 expected = new Point2(0.1, 0.2);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test
    public void test11_walk() {
        Turtle t = new Turtle(0.2, 0.3, 315, Color.BLACK);
        t.walk(Math.hypot(0.1, 0.1));
        Point2 expected = new Point2(0.3, 0.2);
        assertTrue("walk: incorrect final position", 
        		expected.similarTo(t.getPosition(), 1e-6));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test12_walkThrows() {
    	Turtle t = new Turtle();
    	t.walk(-0.0001);
    }
    

    @Test
    public void test13_turnLeft() {
        double ang = 0.0;
        Turtle t = new Turtle(0.1, 0.1, ang, Color.BLACK);
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
			t.turnLeft(delta);
			assertEquals(exp, t.getDirection().getAngle(), Math.ulp(2 * exp));
		}
    }

    @Test
    public void test14_turnRight() {
        double ang = 0.0;
        Turtle t = new Turtle(0.1, 0.1, ang, Color.BLACK);
        double delta = 0.0;
		while (delta < 0.5) {
			delta = Math.random();
		}
		double exp = 0.0;
		for (int i = 1; i < 1000; i++) {
			exp -= delta;
			if (exp < 0.0) {
				exp += 360.0;
			}
			t.turnRight(delta);
			assertEquals(exp, t.getDirection().getAngle(), Math.ulp(2 * exp));
		}
    }
    
    @Test
    public void test15_turnTo() {
    	Turtle t = new Turtle();
    	double[] to = {1.1, 361.1, -358.9, 
    			90.0, 360.0 + 90.0, 360.0 * 1e12 + 90, -360.0 * 1e12 - 270};
    	double[] exp= {1.1, 1.1, 1.1, 
    			90.0, 90.0, 90.0, 90.0};
    	for (int i = 0; i < to.length; i++) {
    		t.turnTo(to[i]);
    		assertEquals(exp[i], t.getDirection().getAngle(), 1e-9);
    	}
    }
    
    @Test
    public void test16_getPen() {
    	Color[] color = {Color.BLACK, Color.RED, Color.BLUE};
    	for (int i = 0; i < color.length; i++) {
    		Color c = color[i];
    		Pen p = new Pen(c);
    		Turtle t = new Turtle(0.5, 0.5, 0, c);
    		assertEquals("getPen() returned the wrong pen\n",
    				p, t.getPen());
    		
    		p.off();
    		t.getPen().off();
    		assertEquals("getPen() returned the wrong pen\n",
    				p, t.getPen());
    	}
    }

    @Test
    public void test17_motion() {
    	Turtle[] exp = {
    			new Turtle(0.5837718716620439, 0.5546101961014429, 33.1, Color.BLACK),
    			new Turtle(0.24213498482164436, 0.1895290577910667, 226.9, Color.BLACK),
    			new Turtle(0.14902940323539152, 0.15304137935730477, 201.4, Color.BLACK),
    			new Turtle(0.09918741963109225, 0.3980225555125122, 101.5, Color.BLACK)
    	};
    	Turtle t = new Turtle();
    	t.turnLeft(33.1);
		t.walk(0.1);
		assertTrue(areSimilar(exp[0], t));
		
		t.turnLeft(193.8);
		t.walk(0.5);
		assertTrue(areSimilar(exp[1], t));
		
		t.turnRight(25.5);
		t.walk(0.1);
		assertTrue(areSimilar(exp[2], t));
		
		t.turnRight(99.9);
		t.walk(0.25);
		assertTrue(areSimilar(exp[3], t));
    }
    
    private static boolean areSimilar(Turtle t, Turtle u) {
    	return t.getPosition().similarTo(u.getPosition(), 1e-9) &&
    			Math.abs(t.getDirection().getAngle() - u.getDirection().getAngle()) < 1e-9 &&
    			t.getPen().equals(u.getPen());
    }
}
