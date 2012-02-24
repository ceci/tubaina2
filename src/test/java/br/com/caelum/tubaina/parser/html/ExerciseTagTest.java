package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ExerciseChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.format.html.ExerciseTag;


public class ExerciseTagTest {
	
	@Test
	public void testExerciseTag(){
		String result = new ExerciseTag().parse(new ExerciseChunk(Arrays.<Chunk>asList(new ParagraphChunk("texto do exercicio"))));
		Assert.assertEquals("<ol class=\"exercise\">texto do exercicio</ol>", result);
	}

}
