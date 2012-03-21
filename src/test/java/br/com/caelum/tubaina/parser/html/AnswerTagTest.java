package br.com.caelum.tubaina.parser.html;


import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.AnswerChunk;


public class AnswerTagTest extends AbstractTagTest {
	@Test
	public void testAnswerTag(){
		AnswerChunk answerChunk = new AnswerChunk(text("texto da resposta"));
		String result = getContent(answerChunk);
		Assert.assertEquals(
				String.format("<a class=\"answer\" onclick=\"toogleAnswer('answer%1$s');\">" +
				"Click here for the answer</a><br /><div class=\"answer\" " +
				"id=\"answer%1$s\"><p>texto da resposta</p></div><br/>", answerChunk.getId()), result);
	}
}


