package de.openhpi.capstone1.game.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.core.PApplet;

import java.util.Observable;
import java.util.Observer;

class KeyboardControllerTest {
	
	KeyboardController controller =null;
	TestObserver observer = null;
	TestPApplet applet = null;

	class TestPApplet extends PApplet {
		TestPApplet(){
			super();
			key='a';
			keyPressed=true;
		}
		
		//public char key='a';
		//public boolean keyPressed= true;
	}
	
	class TestObserver implements Observer {
		public char key;
		public boolean pressed;
		@Override
		public void update(Observable o, Object arg) {
			KeyboardController.KeyInfo keyInfo = (KeyboardController.KeyInfo)arg;
			this.pressed = keyInfo.pressed;
			this.key= keyInfo.key;
		}
		
	}
	class TestController extends KeyboardController {
		void setChangedTrue(){
			this.setChanged();
		}
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		char [] defaultKeys = {'a','s','d','f'};
		controller = new KeyboardController();
		observer = new TestObserver();
		controller.addObserver(observer,defaultKeys);
		applet = new TestPApplet();
	};

	@AfterEach
	void tearDown() throws Exception {
	}



	@Test
	void testAddDeleteCountObservers() {
		char [] defaultKeys = {'a','s','d','f'};
		controller = new KeyboardController();
		observer = new TestObserver();
		controller.addObserver(observer,defaultKeys);
		applet = new TestPApplet();

		char [] keys2 = {'a','b','c'};
		int count = controller.countObservers();
		assertEquals(1,count, "count of observers is wrong");
		Observer observer2= new TestObserver();
		controller.addObserver(observer2,keys2);
		count = controller.countObservers();
		assertEquals(2,count, "count of observers is wrong");
		controller.deleteObserver(observer2);
		count = controller.countObservers();
		assertEquals(1,count, "count of observers is wrong");
		controller.deleteObservers();
		count = controller.countObservers();
		assertEquals(0,count, "count of observers is wrong");

	}

	@Test
	void testHandleEvent() {
		char [] defaultKeys = {'a','s','d','f'};
		controller = new KeyboardController();
		observer = new TestObserver();
		controller.addObserver(observer,defaultKeys);
		applet = new TestPApplet();
		// test good case
		controller.handleEvent(applet);
		assertEquals('a', observer.key, "not the right key received");
		assertEquals(true, observer.pressed, "not the right pressed status");
		applet.key='x';
		// test non registered key
		controller.handleEvent(applet);
		assertEquals('a', observer.key, "not the right key received");
		assertEquals(true, observer.pressed, "not the right pressed status");
		// test second key with released
		applet.key='s';
		applet.keyPressed = false;
		controller.handleEvent(applet);
		assertEquals('s', observer.key, "not the right key received");
		assertEquals(false, observer.pressed, "not the right pressed status");
		// test 3. key with released
		applet.key='d';
		applet.keyPressed = false;
		controller.handleEvent(applet);
		assertEquals('d', observer.key, "not the right key received");
		assertEquals(false, observer.pressed, "not the right pressed status");
		// test second key with released
		applet.key='f';
		applet.keyPressed = true ;
		controller.handleEvent(applet);
		assertEquals('f', observer.key, "not the right key received");
		assertEquals(true, observer.pressed, "not the right pressed status");
		
		// add second handler 
		char [] keys2 = {'a','b','c'};
		TestObserver observer2= new TestObserver();
		controller.addObserver(observer2,keys2);

		// test a which is in both handlers
		applet.key='a';
		applet.keyPressed = true ;
		controller.handleEvent(applet);
		assertEquals('a', observer.key, "not the right key received");
		assertEquals(true, observer.pressed, "not the right pressed status");
		assertEquals('a', observer2.key, "not the right key received");
		assertEquals(true, observer2.pressed, "not the right pressed status");
		
		// test b which is only in second handler 
		applet.key='b';
		applet.keyPressed = false;
		controller.handleEvent(applet);
		assertEquals('a', observer.key, "not the right key received");
		assertEquals(true, observer.pressed, "not the right pressed status");
		assertEquals('b', observer2.key, "not the right key received");
		assertEquals(false, observer2.pressed, "not the right pressed status");

	}


	@Test
	void testNotifyObservers() {
		char [] defaultKeys = {'a','s','d','y'};
		TestController controller2 = new TestController();
		observer = new TestObserver();
		controller2.addObserver(observer,defaultKeys);
		controller2.key='y';
		controller2.pressed=true;
		controller2.setChangedTrue();;
		controller2.notifyObservers();
		assertEquals('y', observer.key, "not the right key received");
		assertEquals(true, observer.pressed, "not the right pressed status");

		
	}

}
