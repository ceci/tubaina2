package br.com.caelum.tubaina.parser.latex;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.chunk.TableColumnChunk;

public class TableColumnTagTest extends AbstractTagTest {

	@Test
	public void testTableColumnTag() {
		String result = getContent(new TableColumnChunk(text("texto da coluna")));
		Assert.assertEquals("texto da coluna& ", result);
	}

}
