package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ItemChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.format.html.ItemTag;

public class ItemTagTest {
	@Test
	public void testItem() {
		ItemChunk itemChunk = new ItemChunk(Arrays.<Chunk>asList(new ParagraphChunk("texto do item")));
		String result = new ItemTag().parse(itemChunk);
		Assert.assertEquals("<li>texto do item</li>", result);
	}

}
