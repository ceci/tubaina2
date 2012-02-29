package br.com.caelum.tubaina.parser.latex;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.ExerciseChunk;

public class ExerciseTagTest extends AbstractTagTest {
	@Test
	public void testExerciseTag(){
		String result = getContent(new ExerciseChunk(text("texto do exercicio")));
		Assert.assertEquals("\\label{ex:1}\n\\begin{enumerate}[1)]\ntexto do exercicio\n\\end{enumerate}", result);
	}

}
