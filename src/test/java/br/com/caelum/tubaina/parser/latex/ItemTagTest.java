package br.com.caelum.tubaina.parser.latex;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.ItemChunk;

public class ItemTagTest extends AbstractTagTest {
	@Test
	public void testItem() {
		String result = getContent(new ItemChunk(text("texto do item")));
		Assert.assertEquals("\n\\item{texto do item}\n", result);
	}

}
