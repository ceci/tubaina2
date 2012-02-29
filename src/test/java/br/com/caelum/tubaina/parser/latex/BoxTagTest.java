package br.com.caelum.tubaina.parser.latex;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.BoxChunk;

public class BoxTagTest extends AbstractTagTest {

	@Test
	public void testBox() {
		String result = getContent(createBoxChunk("Título do Box", "Texto do Box"));
		Assert.assertEquals("\\begin{tubainabox}{Título do Box}\nTexto do Box\n\\end{tubainabox}", result);
	}
	
	@Test
	public void testBoxWithMultilineContent() {
		String result = getContent(createBoxChunk("Título do Box", "Texto do Box\n blablabla\n"));
		Assert.assertEquals("\\begin{tubainabox}{Título do Box}\nTexto do Box\n blablabla\n\n\\end{tubainabox}", result);
	}
	
	private BoxChunk createBoxChunk(String title, String text) {
		BoxChunk boxChunk = new BoxChunk(title, text(text));
		return boxChunk;
	}

}
