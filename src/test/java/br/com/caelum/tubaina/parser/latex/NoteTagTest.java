package br.com.caelum.tubaina.parser.latex;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.NoteChunk;

public class NoteTagTest extends AbstractTagTest {
	
	@Test
	public void testNoteTag(){
		String result = getContent(new NoteChunk(text("qualquer texto de nota")));
		Assert.assertEquals("\\begin{tubainabox}{\\instructornote}\nqualquer texto de nota\n\\end{tubainabox}", result);
	}
}
