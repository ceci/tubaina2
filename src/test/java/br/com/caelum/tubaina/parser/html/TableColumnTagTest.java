package br.com.caelum.tubaina.parser.html;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.format.html.TableColumnTag;

public class TableColumnTagTest {
	
	@Test
	public void testTableColumn() {
		TableColumnTag tag = new TableColumnTag();
		String result = tag.parse("algum texto", null);
		Assert.assertEquals("<td>algum texto</td>", result);
	}
	
}
