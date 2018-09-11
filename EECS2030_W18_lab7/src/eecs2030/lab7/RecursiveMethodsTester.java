package eecs2030.lab7;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RecursiveMethodsTester {

	/**
	 * TESTER FOR: removeRepeats
	 */

	@Test
	public void test_removeRepeats_01() {

		String input = "xyyzzz";
		String exp = "xyz";

		String act = RecursiveMethods.removeRepeats(input);
		assertEquals("Failed: removeRepeats(\"xyyzzz\")", exp, act);

	}

	@Test
	public void test_removeRepeats_02() {

		String input = "fffaaar Ouuut";
		String exp = "far Out";

		String act = RecursiveMethods.removeRepeats(input);
		assertEquals("Failed: removeRepeats(\"fffaaar Ouuut\")", exp, act);

	}

	@Test
	public void test_removeRepeats_03() {

		String input = "nooooo   wooorrriiies";
		String exp = "no wories";

		String act = RecursiveMethods.removeRepeats(input);
		assertEquals("Failed: removeRepeats(\"nooooo  wooorrriiies\")", exp, act);

	}

	@Test
	public void test_removeRepeats_04() {

		String input = "Tomorrow";
		String exp = "Tomorow";

		String act = RecursiveMethods.removeRepeats(input);
		assertEquals("Failed: removeRepeats(\"Tomorrow\")", exp, act);

	}

	/**
	 * Tester for: findTarget
	 */

	// indexOfTarget([3,2,1], 1 ) gives index = 2;
	@Test
	public void test_findTarget_01() {

		List<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 1));
		int exp = 2;
		int act = RecursiveMethods.findTarget(list, 1);
		assertEquals("Failed: findTarget([3,2,1], 1);", exp, act);
	}

	// indexOfTarget([3,2,1], 2 ) gives index = 1;
	@Test
	public void test_findTarget_02() {

		List<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 1));
		int exp = 1;
		int act = RecursiveMethods.findTarget(list, 2);
		assertEquals("Failed: findTarget([3,2,], 2);", exp, act);
	}

	// indexOfTarget([3,2,1], 3 ) gives index = 0;
	@Test
	public void test_findTarget_03() {

		List<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 1));
		int exp = 0;
		int act = RecursiveMethods.findTarget(list, 3);
		assertEquals("Failed: findTarget([3,2,1], 3);", exp, act);
	}

	// indexOfTarget([3,2,1], 10 ) gives index = -1;
	@Test
	public void test_findTarget_04() {

		List<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 1));
		int exp = -1;
		int act = RecursiveMethods.findTarget(list, 10);
		assertEquals("Failed: findTarget([3,2,1], 10);", exp, act);
	}

	// indexOfTarget([15,10,8,6,3,0, -13 -15], 10 ) gives index = 1;
	@Test
	public void test_findTarget_05() {

		List<Integer> list = new ArrayList<>(Arrays.asList(15, 10, 8, 6, 3, 0, -13, -15));
		int exp = 1;
		int act = RecursiveMethods.findTarget(list, 10);
		assertEquals("Failed: findTarget([15,10,8,6,3,0,-13,-15], 10);", exp, act);
	}

	// indexOfTarget([15,8,6,3,0, -13 -15], 10 ) gives index = -1;
	@Test
	public void test_findTarget_06() {

		List<Integer> list = new ArrayList<>(Arrays.asList(15, 8, 6, 3, 0, -13, -15));
		int exp = -1;
		int act = RecursiveMethods.findTarget(list, 10);
		assertEquals("Failed: findTarget([15,8,6,3,0,-13,-15], 10);", exp, act);
	}

	/**
	 * TESTS FOR binaryToInt
	 * 
	 */
	@Test
	public void test_binaryToInt_01() {

		String b = "0";
		int exp = 0;
		int act = RecursiveMethods.binaryToInt(b);
		assertEquals("Failed: binaryToInt(\"0\");", exp, act);
	}

	@Test
	public void test_binaryToInt_02() {

		String b = "101";
		int exp = 5;
		int act = RecursiveMethods.binaryToInt(b);
		assertEquals("Failed: binaryToInt(\"101\");", exp, act);
	}

	@Test
	public void test_binaryToInt_03() {

		String b = "11011";
		int exp = 27;
		int act = RecursiveMethods.binaryToInt(b);
		assertEquals("Failed: binaryToInt(\"11011\");", exp, act);
	}

	@Test
	public void test_binaryToInt_04() {

		String b = "111";
		int exp = 7;
		int act = RecursiveMethods.binaryToInt(b);
		assertEquals("Failed: binaryToInt(\"111\");", exp, act);
	}

	@Test
	public void test_binaryToInt_05() {

		String b = "0111";
		int exp = 7;
		int act = RecursiveMethods.binaryToInt(b);
		assertEquals("Failed: binaryToInt(\"0111\");", exp, act);
	}

	/**
	 * TESTS FOR intToBinary
	 * 
	 */
	@Test
	public void test_intToBinary_01() {

		String exp = "0";
		int d = 0;
		String act = RecursiveMethods.intToBinary(d);
		assertEquals("Failed: intToBinary(0);", exp, act);
	}

	@Test
	public void test_intToBinary_02() {

		String exp = "101";
		int d = 5;
		String act = RecursiveMethods.intToBinary(d);
		assertEquals("Failed: intToBinary(5);", exp, act);
	}

	@Test
	public void test_intToBinary_03() {

		String exp = "11011";
		int d = 27;
		String act = RecursiveMethods.intToBinary(d);
		assertEquals("Failed: intToBinary(27);", exp, act);
	}

	@Test
	public void test_intToBinary_04() {

		String exp = "111";
		int d = 7;
		String act = RecursiveMethods.intToBinary(d);
		assertEquals("Failed: intToBinary(7);", exp, act);
	}

	@Test
	public void test_intToBinary_05() {

		String exp = "100011";
		int d = 35;
		String act = RecursiveMethods.intToBinary(d);
		assertEquals("Failed: intToBinary(35);", exp, act);
	}

	/**
	 * TESTS FOR twoGroups
	 * 
	 */

	// twoGroups([6, 6, 5]) → true
	@Test
	public void test_twoGroups_01() {

		List<Integer> nums = Arrays.asList(6, 6, 5);
		boolean exp = true;
		boolean act = RecursiveMethods.twoGroups(nums);
		assertEquals("Failed: twoGroups([6,6,5])", exp, act);
	}

	// twoGroups([5, 5, 6]) → false
	@Test
	public void test_twoGroups_02() {

		List<Integer> nums = Arrays.asList(5, 5, 6);
		boolean exp = false;
		boolean act = RecursiveMethods.twoGroups(nums);
		assertEquals("Failed: twoGroups([5,5,6])", exp, act);
	}

	// twoGroups([10, 7, 5, 5]) → true
	@Test
	public void test_twoGroups_03() {

		List<Integer> nums = Arrays.asList(10, 7, 5, 5);
		boolean exp = true;
		boolean act = RecursiveMethods.twoGroups(nums);
		assertEquals("Failed: twoGroups([10,7,5,5])", exp, act);
	}

	// twoGroups([6, 5, 5, 5, 1]) → false
	@Test
	public void test_twoGroups_04() {

		List<Integer> nums = Arrays.asList(6, 5, 5, 5, 1);
		boolean exp = false;
		boolean act = RecursiveMethods.twoGroups(nums);
		assertEquals("Failed: twoGroups([6, 5, 5, 5, 1])", exp, act);
	}

	// twoGroups([]) → false false OK
	@Test
	public void test_twoGroups_05() {

		List<Integer> nums = Arrays.asList();
		boolean exp = false;
		boolean act = RecursiveMethods.twoGroups(nums);
		assertEquals("Failed: twoGroups([])", exp, act);
	}

	// twoGroups([10, 0, 5, 5]) → false false OK
	@Test
	public void test_twoGroups_06() {

		List<Integer> nums = Arrays.asList(10, 0, 5, 5);
		boolean exp = false;
		boolean act = RecursiveMethods.twoGroups(nums);
		assertEquals("Failed: twoGroups([10,0,5,5])", exp, act);
	}

}
