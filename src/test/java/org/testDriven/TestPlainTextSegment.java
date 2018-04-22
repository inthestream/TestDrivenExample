package org.testDriven;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.testDriven.template.PlainText;

public class TestPlainTextSegment {
	@Test
	public void plainTextEvaluatesAsIs() throws Exception {
		Map<String, String> variables = new HashMap<String, String>();
		String text = "abc def";
		assertEquals(text, new PlainText(text).evaluate(variables));
	}
}
