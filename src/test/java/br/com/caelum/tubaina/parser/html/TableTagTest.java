package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.TableChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;

public class TableTagTest extends AbstractTagTest {
	
	@Test
	public void testTable() {
		String result = getContent(createTableChunk("texto da tabela", ""));
		Assert.assertEquals("<table border=1><tr><td><p>texto da tabela</p></td></tr></table>", result);
	}
	
	@Test
	public void testTableWithTitle() {
		String result = getContent(createTableChunk("texto da tabela", "\"titulo\""));
		Assert.assertEquals("<h3>titulo</h3><table border=1><tr><td><p>texto da tabela</p></td></tr></table>", result);
	}
	
	@Test
	public void testTableWithoutBorder() {
		String result = getContent(createTableChunk("texto da tabela", "noborder"));
		Assert.assertEquals("<table><tr><td><p>texto da tabela</p></td></tr></table>", result);
	}
	
	@Test
	public void testTableWithTitleAndWithoutBorder() {
		String result = getContent(createTableChunk("texto da tabela", "\"titulo\" noborder"));
		Assert.assertEquals("<h3>titulo</h3><table><tr><td><p>texto da tabela</p></td></tr></table>", result);
	}
	
	private TableChunk createTableChunk(String text, String options) {
		TableColumnChunk column = new TableColumnChunk(text(text));
		TableRowChunk row = new TableRowChunk(Arrays.<Chunk>asList(column));
		TableChunk tableChunk = new TableChunk(options, Arrays.<Chunk>asList(row));
		return tableChunk;
	}
}
