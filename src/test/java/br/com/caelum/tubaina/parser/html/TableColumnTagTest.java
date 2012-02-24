package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.format.html.TableColumnTag;

public class TableColumnTagTest {
	
	@Test
	public void testTableColumn() {
		TableColumnTag tag = new TableColumnTag();
		TableColumnChunk columnChunk = new TableColumnChunk(Arrays.<Chunk>asList(new ParagraphChunk("algum texto")));
		String result = tag.parse(columnChunk);
		Assert.assertEquals("<td>algum texto</td>", result);
	}
	
}
