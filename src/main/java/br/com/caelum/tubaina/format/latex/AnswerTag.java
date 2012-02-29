package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.AnswerChunk;
import br.com.caelum.tubaina.parser.Tag;

public class AnswerTag implements Tag<AnswerChunk> {

	public String parse(AnswerChunk chunk) {
		return "\n\\label{ans:" + chunk.getId() + "}";
	}

}
