package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.chunk.ListType;

public class ListTagTest extends AbstractTagTest {

	@Test
	public void testList() {
		
		String result = getContent(new ListChunk(text("conteudo da lista"), ListType.BULLET));
		Assert.assertEquals("<ul><p>conteudo da lista</p></ul>", result);
	}
	
	@Test
	public void testListNumber() {
		
		String result = getContent(new ListChunk(text("conteudo da lista"), ListType.NUMBER));
		Assert.assertEquals("<ol><p>conteudo da lista</p></ol>", result);
	}
	
	@Test
	public void testListLetter() {
		String result = getContent(new ListChunk(text("conteudo da lista"), ListType.LETTER));
		Assert.assertEquals("<ol class=\"letter\"><p>conteudo da lista</p></ol>", result);
	}
	
	@Test
	public void testListRoman() {
		String result = getContent(new ListChunk(text("conteudo da lista"), ListType.ROMAN));
		Assert.assertEquals("<ol class=\"roman\"><p>conteudo da lista</p></ol>", result);
	}
	
}
