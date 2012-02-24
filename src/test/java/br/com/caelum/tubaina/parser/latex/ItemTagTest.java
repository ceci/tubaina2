package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.builder.ChunkSplitter;
import br.com.caelum.tubaina.chunk.ItemChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;

public class ItemTagTest {
	@Test
	public void testItem() {
		ItemChunk itemChunk = new ItemChunk(Arrays.<Chunk>asList(new ParagraphChunk("texto do item")));
		String result = new ItemTag().parse(itemChunk);
		Assert.assertEquals("\n\\item{texto do item}\n", result);
	}

	@Test
	public void testItemSplit() {
		List<Chunk> chunks = new ChunkSplitter(null, "list").splitChunks("* blah\n\n*bleh\n \n *  blih  ");
		Assert.assertEquals(3, chunks.size());
	}
}
