package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.parser.Tag;
import br.com.caelum.tubaina.util.HtmlSanitizer;

public class ParagraphTag implements Tag<ParagraphChunk> {

	public String parse(ParagraphChunk chunk) {
		
		return "<p>" + new HtmlSanitizer().sanitize(chunk.getContent()) + "</p>";
	}

}
