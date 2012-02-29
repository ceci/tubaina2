package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;

public class TableRowTagTest extends AbstractTagTest {
	
	@Test
	public void testTableRow() {
		String result = getContent(createTableRow("texto"));
		Assert.assertEquals("<tr><td><p>texto</p></td></tr>", result);
	}

	private TableRowChunk createTableRow(String text) {
		TableColumnChunk column = new TableColumnChunk(text(text));
		TableRowChunk row = new TableRowChunk(Arrays.<Chunk>asList(column));
		return row;
	}
}
