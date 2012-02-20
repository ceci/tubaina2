package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.CenteredParagraphChunk;
import br.com.caelum.tubaina.parser.Tag;

public class CenteredParagraphTag implements Tag<CenteredParagraphChunk> {

	public String parse(CenteredParagraphChunk chunk) {
		return "<p class=\"center\">" + chunk.getContent() + "</p>";
	}

}
