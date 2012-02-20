package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.format.html.QuestionTag;


public class QuestionTagTest {

	@Test
	public void testQuestionTag() {
		String result = new QuestionTag().parse("texto da questao", null);
		Assert.assertEquals("<li class=\"question\">texto da questao</li>", result);
	}

	
}
