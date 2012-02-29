package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ItemChunk;
import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.chunk.ListType;

public class ListTagTest extends AbstractTagTest {

	@Test
	public void testList() {
		String result = getContent(new ListChunk(createItem("conteudo da lista"), ListType.BULLET));
		Assert.assertEquals("\\begin{itemize}\n\\item{conteudo da lista}\n\\end{itemize}", result);
	}

	private List<Chunk> createItem(String content) {
		return Arrays.<Chunk>asList(new ItemChunk(text(content)));
	}
	
	@Test
	public void testListNumber() {
		String result = getContent(new ListChunk(createItem("conteudo da lista"), ListType.NUMBER));
		Assert.assertEquals("\\begin{enumerate}[1)]\n\\item{conteudo da lista}\n\\end{enumerate}", result);
	}
	
	@Test
	public void testListLetter() {
		String result = getContent(new ListChunk(createItem("conteudo da lista"), ListType.LETTER));
		Assert.assertEquals("\\begin{enumerate}[a)]\n\\item{conteudo da lista}\n\\end{enumerate}", result);
	}
	
	@Test
	public void testListRoman() {
		String result = getContent(new ListChunk(createItem("conteudo da lista"), ListType.ROMAN));
		Assert.assertEquals("\\begin{enumerate}[I)]\n\\item{conteudo da lista}\n\\end{enumerate}", result);
	}
}
