package br.com.caelum.tubaina.format.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.tubaina.chunk.CodeChunk;
import br.com.caelum.tubaina.parser.Tag;
//XXX: all this should be at the grammar
public class CodeTag implements Tag<CodeChunk> {

	public static final String START_HEADER = "<pre";
	public static final String CLOSE_HEADER = ">\n";
	public static final String END = "\n</pre>";

	public String parse(CodeChunk chunk) {
		String preOptions = classAssembler(chunk.getOptions());
		
		return START_HEADER + preOptions + CLOSE_HEADER + chunk.getContent() + END;
	}
	
	private String classAssembler(String options) {
		String language = detectLanguage(options);
		String numbered = options.contains("#") ? " numbered" : "";
		String highlights = detectHighlights(options);

		return " class=\"code" + language + numbered + "\"" + highlights;
	}
	
	private String detectLanguage(String options) {
		if (options != null) {
			String languageCandidate = options.trim().split(" ")[0];
			if (!languageCandidate.contains("#") && !languageCandidate.startsWith("h=") && !languageCandidate.isEmpty())
				return " " + languageCandidate;
		}
		return " text";
	}
	
	private String detectHighlights(String options) {
		Pattern pattern = Pattern.compile("h=([\\d+,]+)");
		Matcher matcher = pattern.matcher(options);
		if (matcher.find()) {
			return " data-highlight=\"" + matcher.group(1) + "\"";
		}
		return "";
	}
	
}
