package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;

public class TableRowTagTest {
	
	@Test
	public void testTableRowTag() {
		TableRowTag tag = new TableRowTag();
		
		String result = tag.parse(createTableRow("linha da tabela"));
		Assert.assertEquals("linha da tabela\\\\", result);
	}
	
	@Test
	public void testRemoveLastColumnBreak() {
		TableRowTag tag = new TableRowTag();
		String result = tag.parse(createTableRow("coluna1& coluna2& coluna3&"));
		Assert.assertEquals("coluna1& coluna2& coluna3\\\\", result);
	}
	
	private TableRowChunk createTableRow(String text) {
		TableColumnChunk column = new TableColumnChunk(Arrays.<Chunk>asList(new ParagraphChunk(text)));
		TableRowChunk row = new TableRowChunk(Arrays.<Chunk>asList(column));
		return row;
	}
	
}
