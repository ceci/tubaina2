package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.NoteChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;

public class NoteTagTest {
	
	@Test
	public void testNoteTag(){
		NoteChunk noteChunk = new NoteChunk(Arrays.<Chunk>asList(new ParagraphChunk("qualquer texto de nota")));
		String result = new NoteTag().parse(noteChunk);
		Assert.assertEquals("\\begin{tubainabox}{\\instructornote}\nqualquer texto de nota\n\\end{tubainabox}", result);
	}
}
