package com.xq.spstudy.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {
	private Calculator calculator = null;

	@BeforeClass
	public static void beforeClass1() {
		System.out.println("@beforeClass1");
	}

	@BeforeClass
	public static void beforeClass2() {
		System.out.println("@beforeClass2");
	}

	@AfterClass
	public static void afterClass1() {
		System.out.println("@afterClass1");
	}

	@AfterClass
	public static void afterClass2() {
		System.out.println("@afterClass2");
	}

	@Before
	public void before1(){
		System.out.println("before1==========");
		calculator = new Calculator();
		System.out.println(calculator);
	}
	@Before
	public void before2(){
		System.out.println("before2==========");
	}

	@After
	public void after1() {
		System.out.println("after1");
	}
	
	@After
	public void after2(){
		System.out.println("after2");
	}

	@Test
	public void testAdd() {
		int result = calculator.add(1, 2);
		// 判断方法的返回结果
		Assert.assertEquals(3, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	public void testSubtract() {
		int result = calculator.subtract(1, 2);
		// 判断方法的返回结果
		Assert.assertEquals(-1, result);// 第一个参数是期望值，第二个参数是要验证的值

	}
}

class Calculator {
	public int add(int a, int b) {
		return a + b;
	}

	public int subtract(int a, int b) {
		return a - b;
	}
}
