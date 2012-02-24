package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ExerciseChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;

public class ExerciseTagTest {
	@Test
	public void testExerciseTag(){
		ExerciseTag tag = new ExerciseTag();
		String result = tag.parse(new ExerciseChunk(Arrays.<Chunk>asList(new ParagraphChunk("texto do exercicio"))));
		Assert.assertEquals("\\label{ex:1}\n\\begin{enumerate}[1)]\ntexto do exercicio\n\\end{enumerate}", result);
	}

}
