package org.testDriven;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.testDriven.template.Template;

public class TestTemplatePerformance {

	Template template;
	
	@Before
	public void setUp() throws Exception {
		String templateText = "";
		for(int i = 0; i < 100; i++) {
			
			if(i == 99) {
				templateText += "${" + i + "}";
			} else {
				templateText += "${" + i + "}, ";
			}
		}
		
		template = new Template(templateText);
		
		for(int i = 0; i < 100; i++) {
			template.set(i+"", "ahahahahahahah" + i);
		}
		
		System.out.println();
	}
	
	@Test
	public void templateWith100WordsAnd20Variables() throws Exception {
		long expected = 200L;
		long time = System.currentTimeMillis();
		template.evaluate();
		time = System.currentTimeMillis() - time;
		
		System.out.println(time+"ms");
		
		assertTrue("Rendering the template took " + time + "ms while the target was " + expected + "ms", time <= expected);
	}
}
