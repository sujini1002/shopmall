package com.cafe24.shopmall.example;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleTest {
	
	//테스트 케이스(메소드) 끼리 공유해야할 변수가 있으면 static 설정하기
	private static StringBuilder output = new StringBuilder("");

	@BeforeClass
	public static void setUpBefore() {
		System.out.println("@BeforeClass"); // 테스트 시작 전 한번만 실행
	}
	
	@Before
	public void setUp() {
		System.out.println("@Before");
	}
	
	@After
	public void tearDownAfter() { 
		System.out.println("@After");
	}

	@Test
	public void myATest() {
		System.out.println("@Test:A");
		output.append("A");
	}
	@Test
	public void myBTest() {
		System.out.println("@Test:B");
		output.append("B");
	}
	@Test
	public void myCTest() {
		System.out.println("@Test:C");
		output.append("C");
	}
	
	/*
	* assertXYZ();
	* @Ignore - test 안하고 싶을 때 
	*/
	@Ignore
	@Test
	public void ignoreTest() {
		assertTrue(false);
	}
	
	@Test
	public void testAssert1() {
		Object[] a = {"Java","JUnit","Spring"};
		Object[] b = {"Java","JUnit","Spring"};
		
		assertArrayEquals(a, b);
	}
	
	@Test
	public void testAssert() {
		assertTrue(true);
		assertFalse(false);
		
		assertNull(null);
		assertNotNull(new Object());
		
		assertEquals(1+2,3);
		assertEquals(new String("hello"),"hello");//  값비교 / 주소비교 X
		assertNotEquals(true,false);
		
		assertSame("Hello","Hello");
		assertNotSame("Hello",new String("Hello")); // 주소비교 / 값비교X
		assertNotSame(new Integer(1),new Integer(1));
		
		//assertThat : is
		assertThat(1+2,is(3));
		assertThat("this is never",is(not("that is never")));
		
		//assertThat : allOf
		assertThat("Hello World",allOf(startsWith("Hello"),containsString("or")));// 둘다 true가 되어야 결과가 true다

		//assertThat : anyOf : 둘 중에 하나라도 True 이면  True
		assertThat("Hello World",anyOf(startsWith("Heven"),containsString("or")));
		
		//assertThat : both
		assertThat("ABC",both(containsString("A")).and(containsString("B")));
		
		//assertThat : either
		assertThat("ABC",either(containsString("A")).or(containsString("B")));

		//assertThat : everyItem
		assertThat(Arrays.asList("Apple","Application","Apology"),everyItem(startsWith("Ap")));
		
		//assertThat : hasItem
		assertThat(Arrays.asList("Red","Banana","Apple"),hasItem(startsWith("Ap")));
		
		
//		fail("All Over");
	}
	
	@AfterClass
	public static void tearDownAfterClass() { //DB 테스트 후 클린 할 때 사용
		System.out.println("@AfterClass:" + output.toString());
	}
}
