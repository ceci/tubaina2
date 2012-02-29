package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.QuestionChunk;


public class QuestionTagTest extends AbstractTagTest {

	@Test
	public void testQuestionTag() {
		String result = getContent(new QuestionChunk(text("texto da questao")));
		Assert.assertEquals("<li class=\"question\"><p>texto da questao</p></li>", result);
	}

	
}
