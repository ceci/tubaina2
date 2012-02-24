package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.AnswerChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.format.html.AnswerTag;


public class AnswerTagTest {
	@Test
	public void testAnswerTag(){
		AnswerTag tag = new AnswerTag();
		AnswerChunk answerChunk = new AnswerChunk(Arrays.<Chunk>asList(new ParagraphChunk("texto da resposta")));
		String result = tag.parse(answerChunk);
		Assert.assertEquals("<a class=\"answer\" onclick=\"toogleAnswer('answer0');\">" +
				"Click here for the answer</a><br /><div class=\"answer\" " +
				"id=\"answer0\">texto da resposta</div><br/>", result);
	}
}


