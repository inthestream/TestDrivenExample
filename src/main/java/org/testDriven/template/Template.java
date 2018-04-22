package org.testDriven.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testDriven.exception.MissingValueException;

public class Template {

	private Map<String, String> variables;
	private String templateText;
	
	public Template(String templateText) {
		this.variables = new HashMap<String, String>();
		this.templateText = templateText;
	}
	
	public void set(String name, String value) {
		this.variables.put(name, value);
	}
	
	public String evaluate() {
		TemplateParse p = new TemplateParse();
		List<Segment> segments = p.parseSegments(templateText);
		return concatenate(segments);
	}
	
	private String concatenate(List<Segment> segments) {
		StringBuilder result = new StringBuilder();
		for(Segment segment : segments) {
			result.append(segment.evaluate(variables));
		}
		
		return result.toString();
	}
	
	/*private void append(String segment, StringBuilder result) {
		if(isVariable(segment)) {
			evaluateVariable(segment, result);
		} else {
			result.append(segment);
		}
	}*/
	
	public static boolean isVariable(String segment) {
		return segment.startsWith("${") && segment.endsWith("}");
	}
	
	/*private void evaluateVariable(String segment, StringBuilder result) {
		String var = segment.substring(2, segment.length() - 1);
		if(!variables.containsKey(var)) {
			throw new MissingValueException("No value for " + segment);
		}
		result.append(variables.get(var));
	}*/
	
	/*private String replaceVariables() {
		String result = templateText;
		for(Entry<String, String> entry : variables.entrySet()) {
			String regex = "\\$\\{" + entry.getKey() + "\\}";
			result = result.replaceAll(regex, entry.getValue());
		}
		return result;
	}
	
	private void checkForMissingValues(String result) {
		Matcher m = Pattern.compile("\\$\\{.+\\}").matcher(result);
		
		if(m.find()) {
			throw new MissingValueException("No value for " + m.group());
		}
	}*/

}
