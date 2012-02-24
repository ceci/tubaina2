package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.chunk.ListType;
import br.com.caelum.tubaina.chunk.ParagraphChunk;

public class ListTagTest {

	private ListTag tag;

	@Before
	public void setUp() {
		tag = new ListTag();
	}
	
	@Test
	public void testList() {
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("conteudo da lista")), ListType.BULLET));
		Assert.assertEquals("\\begin{itemize}\nconteudo da lista\\end{itemize}", result);
	}
	
	@Test
	public void testListNumber() {
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("conteudo da lista")), ListType.NUMBER));
		Assert.assertEquals("\\begin{enumerate}[1)]\nconteudo da lista\n\\end{enumerate}", result);
	}
	
	@Test
	public void testListLetter() {
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("conteudo da lista")), ListType.LETTER));
		Assert.assertEquals("\\begin{enumerate}[a)]\nconteudo da lista\n\\end{enumerate}", result);
	}
	
	@Test
	public void testListRoman() {
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("conteudo da lista")), ListType.ROMAN));
		Assert.assertEquals("\\begin{enumerate}[I)]\nconteudo da lista\n\\end{enumerate}", result);
	}
}
