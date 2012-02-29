package br.com.caelum.tubaina.parser.latex;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;

public class TableRowTagTest extends AbstractTagTest {
	
	@Test
	public void testTableRowTag() {
		String result = getContent(createTableRow("linha da tabela"));
		Assert.assertEquals("linha da tabela\\\\", result);
	}
	
	@Test
	public void testRemoveLastColumnBreak() {
		String result = getContent(createTableRow("coluna1", "coluna2", "coluna3"));
		Assert.assertEquals("coluna1& coluna2& coluna3\\\\", result);
	}
	
	private TableRowChunk createTableRow(String... texts) {
		List<Chunk> columns = new ArrayList<Chunk>();
		for (String text : texts) {
			columns.add(new TableColumnChunk(text(text)));
		}
		TableRowChunk row = new TableRowChunk(columns);
		return row;
	}
	
}
