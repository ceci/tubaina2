package br.com.caelum.tubaina.parser.html;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.chunk.TableColumnChunk;

public class TableColumnTagTest extends AbstractTagTest {
	
	@Test
	public void testTableColumn() {
		String result = getContent(new TableColumnChunk(text("algum texto")));
		Assert.assertEquals("<td><p>algum texto</p></td>", result);
	}
	
}
