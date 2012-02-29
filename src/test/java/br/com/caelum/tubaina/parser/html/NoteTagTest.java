package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.NoteChunk;

public class NoteTagTest extends AbstractTagTest{

	@Test
	public void testNoteTag(){
		String result = getContent(new NoteChunk(text("qualquer texto de nota")));
		Assert.assertEquals(result, "<div class=\"note\"><p>qualquer texto de nota</p></div>");
	}
	
}
