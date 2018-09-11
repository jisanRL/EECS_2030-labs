package eecs2030.lab6;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.rules.Timeout;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QueueTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2);

	@Test
	public void test00_fields() {
		try {
			List<Field> fields = Arrays.asList(eecs2030.lab6.Queue.class.getDeclaredFields());
			for (Field f : fields) {
				String name = f.getName();
				if (name.equals("front")) {
					if (f.getType() != eecs2030.lab6.Node.class) {
						fail("this.front has the wrong type");
					}
				} else if (name.equals("back")) {
					if (f.getType() != eecs2030.lab6.Node.class) {
						fail("this.back has the wrong type");
					}
				} else if (name.equals("size")) {
					if (f.getType() != Integer.TYPE) {
						fail("this.size has the wrong type");
					}
				} else {
					fail("there is a field named: " + name + " that should not be in the class");
				}
			}
		} catch (Exception x) {
			fail("exception occurred trying to get the fields of this class");
		}

	}

	@Test
	public void test01_ctor() {
		Queue<String> q = new Queue<>();
		assertNull("this.back is not null", q.getBack());
		assertNull("this.front is not null", q.getFront());
		assertEquals("this.size is not 0", 0, q.size());
	}

	/**
	 * Checks that the structure of the queue got matches the structure of the
	 * list exp. Checks that the queue has the correct size, front node, and
	 * back node, and that the elements in the queue equal the elements the
	 * expected list.
	 * 
	 * @param exp
	 *            the expected list
	 * @param got
	 *            the queue to check
	 */
	private <E> void checkStructure(List<E> exp, eecs2030.lab6.Queue<E> got) {
		if (exp.isEmpty()) {
			if (got.size() != 0) {
				fail("expected an empty list; got a queue of size: " + got.size());
			}
			if (got.getFront() != null) {
				fail("expected an empty list; got a queue with a non-null front node");
			}
			if (got.getBack() != null) {
				fail("expected an empty list; got a queue with a non-null back node");
			}
		} else {
			if (exp.size() != got.size()) {
				String err = String.format("queue has the wrong size; expected size: %d, got size: %d", exp.size(),
						got.size());
				fail(err);
			}
			Node<E> n = got.getFront();
			for (int i = 0; i < exp.size(); i++) {
				E expData = exp.get(i);
				E gotData = n.getElement();
				if (!expData.equals(gotData)) {
					String err = String.format("node: %d has the wrong element; expected: %s but got: %s", i, expData,
							gotData);
					fail(err);
				}
				if (i == exp.size() - 1) {
					// at the back node
					if (got.getBack() != n) {
						fail("this.back is pointing to the wrong node");
					}
				}
				n = n.getNext();
				if (n == null && i != exp.size() - 1) {
					String err = String.format("node: %d has an unexpected null next link", i);
					fail(err);
				}
			}
			if (n != null) {
				fail("this.back has a non-null next link");
			}
		}
	}

	@Test
	public void test01_enqueue() {
		Queue<String> q = new Queue<>();
		q.enqueue("abc");

		List<String> t = Arrays.asList("abc");
		this.checkStructure(t, q);
	}

	@Test
	public void test02_enqueue() {
		Queue<Integer> q = new Queue<>();
		q.enqueue(100);
		q.enqueue(200);

		List<Integer> t = Arrays.asList(100, 200);
		this.checkStructure(t, q);
	}

	@Test
	public void test03_enqueue() {
		Queue<Integer> q = new Queue<>();
		q.enqueue(100);
		q.enqueue(200);
		q.enqueue(300);
		q.enqueue(400);
		q.enqueue(0);

		List<Integer> t = Arrays.asList(100, 200, 300, 400, 0);
		this.checkStructure(t, q);
	}
	
	
	private static <E> Queue<E> makeQueue(List<E> t) {
		Queue<E> q = new Queue<>();
		if (t.isEmpty()) {
			return q;
		}
		try {
			Class<?> c = q.getClass();

			Node<E> n = new Node<>(t.get(0), null);
			Node<E> front = n;
			Node<E> back = n;
			for (int i = 1; i < t.size(); i++) {
				back = new Node<>(t.get(i), null);
				n.setNext(back);
				n = back;
			}
			
			// try to set q.front
			Field f = c.getDeclaredField("front");
			f.setAccessible(true);
			f.set(q, front);

			// try to set q.back and q.size
			Field b = c.getDeclaredField("back");
			b.setAccessible(true);
			b.set(q, back);

			Field s = c.getDeclaredField("size");
			s.setAccessible(true);
			s.setInt(q, t.size());

		} catch (NoSuchFieldException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		}
		return q;
		
	}
	

	@Test
	public void test04_dequeue() {
		List<String> t = new ArrayList<>(Arrays.asList("abc"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();
		
		t.remove(0);
		this.checkStructure(t, q);
	}
	
	@Test
	public void test05_dequeue() {
		List<String> t = new ArrayList<>(Arrays.asList("abc", "def"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();

		t.remove(0);
		this.checkStructure(t, q);
	}
	
	@Test
	public void test06_dequeue() {
		List<String> t = new ArrayList<>(Arrays.asList("abc", "def", "xyz"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();

		t.remove(0);
		this.checkStructure(t, q);
	}
	
	@Test
	public void test07_dequeue() {
		List<String> t = new ArrayList<>(Arrays.asList("abc", "def", "xyz"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();
		q.dequeue();

		t.remove(0);
		t.remove(0);
		this.checkStructure(t, q);
	}
	
	@Test
	public void test08_dequeue() {
		List<String> t = new ArrayList<>(Arrays.asList("1", "2", "3"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();
		q.dequeue();
		q.dequeue();

		t.remove(0);
		t.remove(0);
		t.remove(0);
		this.checkStructure(t, q);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void test09_dequeue() {
		Queue<Double> q = new Queue<Double>();
		q.dequeue();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void test10_dequeue() {
		List<String> t = new ArrayList<>(Arrays.asList("1", "2", "3"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void test11_peek() {
		Queue<Double> q = new Queue<Double>();
		q.peek();
	}
	
	@Test
	public void test12_peek() {
		List<String> t = new ArrayList<>(Arrays.asList("1", "2", "3"));
		Queue<String> q = QueueTest.makeQueue(t);
		String got = q.peek();
		assertEquals("peek returned the wrong element",
				t.get(0), got);
	}
	
	@Test
	public void test13_peek() {
		List<String> t = new ArrayList<>(Arrays.asList("1", "2", "3"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();
		String got = q.peek();
		assertEquals("peek returned the wrong element",
				t.get(1), got);
	}
	
	@Test
	public void test14_peek() {
		List<String> t = new ArrayList<>(Arrays.asList("1", "2", "3"));
		Queue<String> q = QueueTest.makeQueue(t);
		q.dequeue();
		q.dequeue();
		String got = q.peek();
		assertEquals("peek returned the wrong element",
				t.get(2), got);
	}
	
	@Test
	public void test15_size() {
		Queue<Double> q = new Queue<Double>();
		int got = q.size();
		assertEquals("size returned the wrong value",
				0, got);
	}
	
	@Test
	public void test16_size() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(1.0);
		int got = q.size();
		assertEquals("size returned the wrong value",
				1, got);
	}
	
	@Test
	public void test17_size() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(1.0);
		q.enqueue(2.0);
		int got = q.size();
		assertEquals("size returned the wrong value",
				2, got);
	}
	
	@Test
	public void test18_size() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(1.0);
		q.enqueue(2.0);
		q.enqueue(3.0);
		int got = q.size();
		assertEquals("size returned the wrong value",
				3, got);
	}
	
	@Test
	public void test19_size() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(1.0);
		q.enqueue(2.0);
		q.enqueue(3.0);
		q.enqueue(4.0);
		q.enqueue(5.0);
		q.enqueue(6.0);
		q.dequeue();
		int got = q.size();
		assertEquals("size returned the wrong value",
				5, got);
	}
	
	@Test
	public void test20_size() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(1.0);
		q.dequeue();
		q.enqueue(2.0);
		q.enqueue(3.0);
		q.enqueue(4.0);
		q.enqueue(5.0);
		q.enqueue(6.0);
		q.dequeue();
		q.enqueue(7.0);
		int got = q.size();
		assertEquals("size returned the wrong value",
				5, got);
	}
	
	@Test
	public void test21_isEmpty() {
		Queue<Double> q = new Queue<Double>();
		assertTrue("isEmpty returned false for an empty queue",
				q.isEmpty());
	}
	
	@Test
	public void test22_isEmpty() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(2.0);
		q.dequeue();
		assertTrue("isEmpty returned false for an empty queue",
				q.isEmpty());
	}
	
	@Test
	public void test23_isEmpty() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(0.0);
		assertFalse("isEmpty returned true for an non-empty queue",
				q.isEmpty());
	}
	
	@Test
	public void test24_isEmpty() {
		Queue<Double> q = new Queue<Double>();
		q.enqueue(0.0);
		q.dequeue();
		q.enqueue(1.0);
		assertFalse("isEmpty returned true for an non-empty queue",
				q.isEmpty());
	}
	
	@Test
	public void test25_toString() {
		Queue<Double> q = new Queue<Double>();
		assertEquals("[]", q.toString());
	}
	
	@Test
	public void test26_toString() {
		Queue<String> q = new Queue<>();
		q.enqueue("hi");		
		assertEquals("[hi]", q.toString());
	}
	
	@Test
	public void test27_toString() {
		Queue<String> q = new Queue<>();
		q.enqueue("hi");
		q.enqueue("high");
		assertEquals("[hi, high]", q.toString());
	}
	
	@Test
	public void test28_toString() {
		Queue<String> q = new Queue<>();
		q.enqueue("hi");
		q.enqueue("high");
		q.enqueue("why");
		assertEquals("[hi, high, why]", q.toString());
	}
	
	@Test
	public void test29_toString() {
		Queue<Integer> q = new Queue<>();
		q.enqueue(0);
		q.enqueue(1);
		q.dequeue();
		q.enqueue(2);
		q.dequeue();
		q.enqueue(3);
		assertEquals("[2, 3]", q.toString());
	}
}
