package br.com.caelum.tubaina.parser.latex;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.CenteredParagraphChunk;

public class CenteredParagraphTagTest extends AbstractTagTest {
	@Test
	public void testCenteredParagraphTag() {
		String result = getContent(new CenteredParagraphChunk("texto centralizado"));
		Assert.assertEquals("\\begin{center}texto centralizado\\end{center}",
				result);
	}
}
