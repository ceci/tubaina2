package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;
import br.com.caelum.tubaina.format.html.TableRowTag;

public class TableRowTagTest {
	
	@Test
	public void testTableRow() {
		TableRowTag tag = new TableRowTag();
		String result = tag.parse(createTableRow("texto"));
		Assert.assertEquals("<tr>texto</tr>", result);
	}

	private TableRowChunk createTableRow(String text) {
		TableColumnChunk column = new TableColumnChunk(Arrays.<Chunk>asList(new ParagraphChunk(text)));
		TableRowChunk row = new TableRowChunk(Arrays.<Chunk>asList(column));
		return row;
	}
}
