package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.CenteredParagraphChunk;
import br.com.caelum.tubaina.parser.Tag;

public class CenteredParagraphTag implements Tag<CenteredParagraphChunk> {

	public String parse(CenteredParagraphChunk chunk) {
		return "\\begin{center}" + chunk.getContent() + "\\end{center}";
	}

}
