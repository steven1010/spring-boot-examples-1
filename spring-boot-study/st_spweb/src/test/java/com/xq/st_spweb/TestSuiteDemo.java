package com.xq.st_spweb;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteDemo extends TestSuite {

	public static Test suite() {
		// 创建TestSuite对象
		TestSuite suite = new TestSuite();

		// 为TestSuite添加一个测试用例集合
		// 通过参数可以知道，其实该参数就是TestCase的子类
		suite.addTestSuite(TestCaseDemo.class);

		// 创建具体的测试用例
		Test test = TestSuite.createTest(TestCaseDemo.class, "testApp1");

		// 添加一个具体的测试用例
		suite.addTest(test);

		return suite;
	}
}