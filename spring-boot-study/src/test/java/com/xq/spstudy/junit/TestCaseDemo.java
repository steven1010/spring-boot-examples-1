package com.xq.spstudy.junit;

import junit.framework.TestCase;

public class TestCaseDemo extends TestCase {
	public TestCaseDemo(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		System.out.println("AppTest.setUp");
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();

		System.out.println("AppTest.tearDown");
	}

	// 这里以test开头的函数都是测试用例
	public void testApp1() {
		assertTrue(true);
	}

	public void testApp2() {
		assertTrue(true);
	}

}
