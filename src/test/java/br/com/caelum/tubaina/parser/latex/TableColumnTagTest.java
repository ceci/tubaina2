package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;

public class TableColumnTagTest {

	@Test
	public void testTableColumnTag() {
		TableColumnTag tag = new TableColumnTag();
		String result = tag.parse(new TableColumnChunk(Arrays.<Chunk>asList(new ParagraphChunk("texto da coluna"))));
		Assert.assertEquals("texto da coluna& ", result);
	}

}
