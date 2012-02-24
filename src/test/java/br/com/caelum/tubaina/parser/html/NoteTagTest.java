package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.NoteChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.format.html.NoteTag;

public class NoteTagTest {

	@Test
	public void testNoteTag(){
		NoteChunk noteChunk = new NoteChunk(Arrays.<Chunk>asList(new ParagraphChunk("qualquer texto de nota")));
		String result = new NoteTag().parse(noteChunk);
		Assert.assertEquals(result, "<div class=\"note\">qualquer texto de nota</div>");
	}
	
}
