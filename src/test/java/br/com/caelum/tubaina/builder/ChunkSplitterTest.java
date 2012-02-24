package br.com.caelum.tubaina.builder;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;


public class ChunkSplitterTest {
	@Test
	public void testItemSplit() {
		List<Chunk> chunks = new ChunkSplitter(null, "list")
				.splitChunks("* blah\n\n*bleh\n \n *  blih  ");
		Assert.assertEquals(3, chunks.size() );
	}
}
