package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.ExerciseChunk;


public class ExerciseTagTest extends AbstractTagTest {
	
	@Test
	public void testExerciseTag(){
		String result = getContent(new ExerciseChunk(text("texto do exercicio")));
		Assert.assertEquals("<ol class=\"exercise\"><p>texto do exercicio</p></ol>", result);
	}

}
