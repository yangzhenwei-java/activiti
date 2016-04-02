//package me.zeph.relations.integration;
//
//import com.google.common.collect.Lists;
//import me.zeph.relations.config.WebContextConfiguration;
//import me.zeph.relations.model.OneParentLocusRecord;
//import me.zeph.relations.model.Unit;
//import me.zeph.relations.service.Calculator;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestContextManager;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.web.WebAppConfiguration;
//import java.util.List;
//import static org.junit.Assert.assertEquals;
//import static org.junit.runners.Parameterized.Parameters;
//
//@RunWith(Parameterized.class)
//@ContextConfiguration(classes = WebContextConfiguration.class)
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
//@WebAppConfiguration
//public class CalculatorIntegrationTest {
//	@Autowired
//	private Calculator calculator;
//	private Unit c1;
//	private Unit c2;
//	private Unit af1;
//	private Unit af2;
//	private double expectedPi;
//	private final TestContextManager testContextManager;
//	private static final double DELTA = 0.0000001;
//	private static final Unit A14 = new Unit(14, 0.0393d);
//	private static final Unit A15 = new Unit(15, 0.3541d);
//	private static final Unit A16 = new Unit(16, 0.3410d);
//
//	public CalculatorIntegrationTest(Unit c1, Unit c2, Unit af1, Unit af2, double expectedPi) {
//		this.c1 = c1;
//		this.c2 = c2;
//		this.af1 = af1;
//		this.af2 = af2;
//		this.expectedPi = expectedPi;
//		this.testContextManager = new TestContextManager(getClass());
//	}
//
//	@Parameters
//	public static List<Object[]> data() {
//		return Lists.newArrayList(new Object[][] { { A14, A15, A14, A15, 7.06733840514568d } });
//	}
//
//	@Before
//	public void injectDependencies() throws Throwable {
//		this.testContextManager.prepareTestInstance(this);
//	}
//
//	@Test
//	public void shouldFindFormulaByPattenAndCalculatePi() {
//		OneParentLocusRecord record = new OneParentLocusRecord(c1, c2, af1, af2);
//		double pi = calculator.calculatePi(record.getPattern(), record.getP(), record.getQ());
//		assertEquals(expectedPi, pi, DELTA);
//	}
//}