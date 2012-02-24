package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.QuestionChunk;
import br.com.caelum.tubaina.format.html.QuestionTag;


public class QuestionTagTest {

	@Test
	public void testQuestionTag() {
		QuestionChunk questionChunk = new QuestionChunk(Arrays.<Chunk>asList(new ParagraphChunk("texto da questao")));
		String result = new QuestionTag().parse(questionChunk);
		Assert.assertEquals("<li class=\"question\">texto da questao</li>", result);
	}

	
}
