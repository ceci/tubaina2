package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.CenteredParagraphChunk;

public class CenteredParagraphTagTest extends AbstractTagTest {
	@Test
	public void testCenteredParagraphTest() {
		String result = getContent(new CenteredParagraphChunk("texto centralizado"));
		Assert.assertEquals("<p class=\"center\">texto centralizado</p>", result);
	}
}
