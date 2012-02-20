package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ParagraphTag implements Tag<ParagraphChunk> {

	public String parse(ParagraphChunk chunk) {
		return "<p>" + chunk.getContent() + "</p>";
	}

}
