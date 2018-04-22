package org.testDriven;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.testDriven.template.PlainText;
import org.testDriven.template.Segment;
import org.testDriven.template.TemplateParse;
import org.testDriven.template.Variable;

public class TestTemplateParse {

	private List<String> parse(String template) {
		return new TemplateParse().parse(template);
	}
	
	private void assertSegments(List<? extends Object> actual, Object... expected) {
		assertEquals("Number of segments doesn't match.", expected.length, actual.size());
	}
	
	@Test
	public void emptyTemplateRendersAsEmptyString() throws Exception {
		List<String> segments = parse("");
		assertSegments(segments, "");
	}
	
	@Test
	public void templateWithOnlyPlainText() throws Exception {
		List<String> segments = parse("plain text only");
		assertSegments(segments, "plain text only");
	}
	
	@Test
	public void parsingMultipleVariables() throws Exception {
		List<String> segments = parse("${a}:${b}:${c}");
		assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
	}
	
	@Test 
	public void parsingTemplateIntoSegmentObject() throws Exception {
		TemplateParse p = new TemplateParse();
		List<Segment> segments = p.parseSegments("a ${b} c ${d}");
		assertSegments(segments, new PlainText("a "), new Variable("b"),
								 new PlainText(" c "), new Variable("d"));
	}
}
