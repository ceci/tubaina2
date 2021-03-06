package br.com.caelum.tubaina.parser.latex;

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
		Assert.assertEquals(
				"\\begin{table}[!h]\n" +
				"\\caption{}\n" +
				"\\begin{center}\n" +
				"\\rowcolors[]{1}{gray!30}{gray!15}\n" +
				"\\begin{tabularx}{X}\n" +
				"\\hline\n" +
				"texto da tabela\\\\\n" +
				"\\hline\n" +
				"\\end{tabularx}\n\\end{center}\n\\end{table}", result);
	}

	@Test
	public void testTableWithTitle() {
		String result = getContent(createTableChunk("texto da tabela", "\"titulo\""));
		Assert.assertEquals(
				"\\begin{table}[!h]\n" +
				"\\caption{titulo}\n" +
				"\\begin{center}\n" +
				"\\rowcolors[]{1}{gray!30}{gray!15}\n" +
				"\\begin{tabularx}{X}\n" +
				"\\hline\n" +
				"texto da tabela\\\\\n" +
				"\\hline\n" +
				"\\end{tabularx}\n\\end{center}\n\\end{table}", result);
	}

	@Test
	public void testTableWithoutBorder() {
		String result = getContent(createTableChunk("texto da tabela", "noborder"));
		Assert.assertEquals(
				"\\begin{table}[!h]\n" +
				"\\caption{}\n" +
				"\\begin{center}\n" +
				"\\begin{tabularx}{X}\n" +
				"texto da tabela\\\\\n" +
				"\\end{tabularx}\n\\end{center}\n\\end{table}", result);
	}

	@Test
	public void testTableWithTitleAndWithoutBorder() {
		String result = getContent(createTableChunk("texto da tabela", "\"titulo\" noborder"));
		Assert.assertEquals(
				"\\begin{table}[!h]\n" +
				"\\caption{titulo}\n" +
				"\\begin{center}\n" +
				"\\begin{tabularx}{X}\n" +
				"texto da tabela\\\\\n" +
				"\\end{tabularx}\n\\end{center}\n\\end{table}", result);
	}
	
	private TableChunk createTableChunk(String text, String options) {
		TableColumnChunk column = new TableColumnChunk(text(text));
		TableRowChunk row = new TableRowChunk(Arrays.<Chunk>asList(column));
		TableChunk tableChunk = new TableChunk(options, Arrays.<Chunk>asList(row));
		return tableChunk;
	}
}
