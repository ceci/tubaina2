package br.com.caelum.tubaina.parser.latex;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.CenteredParagraphChunk;

public class CenteredParagraphTagTest {
	@Test
	public void testCenteredParagraphTag() {
		CenteredParagraphTag tag = new CenteredParagraphTag();
		String result = tag.parse(new CenteredParagraphChunk("texto centralizado"));
		Assert.assertEquals("\\begin{center}texto centralizado\\end{center}",
				result);
	}
}
