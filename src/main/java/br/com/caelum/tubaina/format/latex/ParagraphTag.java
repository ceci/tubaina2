package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ParagraphTag implements Tag<ParagraphChunk> {

	public String parse(ParagraphChunk chunk) {
		return "\n\n" + chunk.getContent();
	}

}
