package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.format.html.CenteredParagraphTag;

public class CenteredParagraphTagTest {
	@Test
	public void testCenteredParagraphTest() {
		CenteredParagraphTag tag = new CenteredParagraphTag();
		String result = tag.parse("texto centralizado", null);
		Assert.assertEquals("<p class=\"center\">texto centralizado</p>", result);
	}
}
