package de.openhpi.capstone1.game.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Vector2dTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVector2dFloatFloat() {
		Vector2d v = new Vector2d(7,13);
		assertEquals(7f, v.x ,"x of new vector is not 7");
		assertEquals(13f, v.y, "y of new vector is not 13");
	}

	@Test
	void testVector2d() {
		Vector2d v = new Vector2d();
		assertEquals(0f, v.x ,"x of new vector is not 0");
		assertEquals(0f, v.y, "y of new vector is not 0");
	}

	@Test
	void testAddVector2dVector2d() {
		Vector2d v1 = new Vector2d(7,13);
		Vector2d v2 = new Vector2d(13,17);
		Vector2d v3 = Vector2d.add(v1,v2);
		assertEquals(20f, v3.x ,"x of new vector is not sum of x1 + x2");
		assertEquals(30f, v3.y, "y of new vector is not sum of y1 + y2");
	}

	@Test
	void testAddVector2dFloatFloat() {
		Vector2d v1 = new Vector2d(7,13);
		Vector2d v3 = Vector2d.add(v1,13,-17);
		assertEquals(20f, v3.x ,"x of new vector is not sum of x1 + x2");
		assertEquals(-4f, v3.y, "y of new vector is not sum of y1 + y2");
	}

	@Test
	void testAddVector2d() {
		Vector2d v1 = new Vector2d(7,13);
		Vector2d v2 = new Vector2d(13,17);
		v1.add(v2);
		assertEquals(20f, v1.x ,"x of new vector is not sum of x1 + x2");
		assertEquals(30f, v1.y, "y of new vector is not sum of y1 + y2");
	}

	@Test
	void testSubVector2dVector2d() {
		Vector2d v1 = new Vector2d(7,13);
		Vector2d v2 = new Vector2d(13,17);
		Vector2d v3 =Vector2d.sub(v1,v2);
		assertEquals(-6f, v3.x ,"x of new vector is not sum of x1 + x2");
		assertEquals(-4f, v3.y, "y of new vector is not sum of y1 + y2");
	}

	@Test
	void testSubVector2dFloatFloat() {
		Vector2d v1 = new Vector2d(7,13);
		Vector2d v3 =Vector2d.sub(v1,13f,17f);
		assertEquals(-6f, v3.x ,"x of new vector is not sum of x1 + x2");
		assertEquals(-4f, v3.y, "y of new vector is not sum of y1 + y2");
	}

	@Test
	void testSubVector2d() {
		Vector2d v1 = new Vector2d(7,13);
		Vector2d v2 = new Vector2d(13,17);
		v1.sub(v2);
		assertEquals(-6f, v1.x ,"x of new vector is not sum of x1 + x2");
		assertEquals(-4f, v1.y, "y of new vector is not sum of y1 + y2");
	}

	@Test
	void testLength() {
		Vector2d v1 = new Vector2d(4,3);
		float l = v1.length();
		assertEquals(5f, l, "length vector is correct");
	}

	@Test
	void testCopy() {
		Vector2d v1 = new Vector2d(4,3);
		Vector2d v2 = v1.copy();
		v1.x = 7;
		assertEquals(4f, v2.x ,"x of new vector is not the same as the original one ");
		assertEquals(3f, v2.y, "y of new vector is not the same as the original one ");
			
	}

	@Test
	void testNormalize() {
		fail("Not yet implemented");
	}

	@Test
	void testMult() {
		fail("Not yet implemented");
	}

	@Test
	void testHeading() {
		fail("Not yet implemented");
	}

	@Test
	void testRotateVector2dVector2dFloat() {
		fail("Not yet implemented");
	}

	@Test
	void testRotateFloat() {
		fail("Not yet implemented");
	}

}
