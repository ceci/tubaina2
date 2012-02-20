package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.AnswerChunk;
import br.com.caelum.tubaina.parser.Tag;

public class AnswerTag implements Tag<AnswerChunk> {
	private final String answerText = "Click here for the answer";
	
	public String parse(AnswerChunk chunk) {
		return "<a class=\"answer\" onclick=\"toogleAnswer('answer" + chunk.getId() + "');\">"
		+ answerText + "</a><br /><div class=\"answer\" id=\"answer" + chunk.getId()
		+ "\">" + chunk.getContent() + "</div><br/>";
	}

}
