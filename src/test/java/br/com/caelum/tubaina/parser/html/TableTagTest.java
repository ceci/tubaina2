package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.TableChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;
import br.com.caelum.tubaina.format.html.TableTag;

public class TableTagTest {
	
	@Test
	public void testTable() {
		TableTag tag = new TableTag();
		String result = tag.parse(createTableChunk("texto da tabela", ""));
		Assert.assertEquals("<table border=1>texto da tabela</table>", result);
	}
	
	@Test
	public void testTableWithTitle() {
		TableTag tag = new TableTag();
		String result = tag.parse(createTableChunk("texto da tabela", "\"titulo\""));
		Assert.assertEquals("<h3>titulo</h3><table border=1>texto da tabela</table>", result);
	}
	
	@Test
	public void testTableWithoutBorder() {
		TableTag tag = new TableTag();
		String result = tag.parse(createTableChunk("texto da tabela", "noborder"));
		Assert.assertEquals("<table>texto da tabela</table>", result);
	}
	
	@Test
	public void testTableWithTitleAndWithoutBorder() {
		TableTag tag = new TableTag();
		String result = tag.parse(createTableChunk("texto da tabela", "\"titulo\" noborder"));
		Assert.assertEquals("<h3>titulo</h3><table>texto da tabela</table>", result);
	}
	
	private TableChunk createTableChunk(String text, String options) {
		TableColumnChunk column = new TableColumnChunk(Arrays.<Chunk>asList(new ParagraphChunk(text)));
		TableRowChunk row = new TableRowChunk(Arrays.<Chunk>asList(column));
		TableChunk tableChunk = new TableChunk(options, Arrays.<Chunk>asList(row));
		return tableChunk;
	}
}
