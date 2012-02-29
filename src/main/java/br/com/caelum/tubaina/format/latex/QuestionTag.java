package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.QuestionChunk;
import br.com.caelum.tubaina.parser.Tag;

public class QuestionTag implements Tag<QuestionChunk> {

	public String parse(QuestionChunk chunk) {
		return "\\item{" + chunk.getContent() + "}";
	}

}
