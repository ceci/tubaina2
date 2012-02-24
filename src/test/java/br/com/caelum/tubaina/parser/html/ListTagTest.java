package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.chunk.ListType;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.format.html.ListTag;

public class ListTagTest {

	@Test
	public void testList() {
		ListTag tag = new ListTag();
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("conteudo da lista")), ListType.BULLET));
		Assert.assertEquals("<ul>conteudo da lista</ul>", result);
	}
	
	@Test
	public void testListNumber() {
		ListTag tag = new ListTag();
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("\nconteudo da lista")), ListType.NUMBER));
		Assert.assertEquals("<ol>conteudo da lista</ol>", result);
	}
	
	@Test
	public void testListLetter() {
		ListTag tag = new ListTag();
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("\nconteudo da lista")), ListType.LETTER));
		Assert.assertEquals("<ol class=\"letter\">conteudo da lista</ol>", result);
	}
	
	@Test
	public void testListRoman() {
		ListTag tag = new ListTag();
		String result = tag.parse(new ListChunk(Arrays.<Chunk>asList(new ParagraphChunk("\nconteudo da lista")), ListType.ROMAN));
		Assert.assertEquals("<ol class=\"roman\">conteudo da lista</ol>", result);
	}
	
}
