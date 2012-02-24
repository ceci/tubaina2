package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.BoxChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;

public class BoxTagTest {

	@Test
	public void testBox() {
		BoxTag tag = new BoxTag();
		String result = tag.parse(createBoxChunk("Título do Box", "Texto do Box"));
		Assert.assertEquals("\\begin{tubainabox}{Titulo do Box}\nTexto do Box\n\\end{tubainabox}", result);
	}
	
	@Test
	public void testBoxWithMultilineContent() {
		BoxTag tag = new BoxTag();
		String result = tag.parse(createBoxChunk("Título do Box", "Texto do Box\n blablabla\n"));
		Assert.assertEquals("\\begin{tubainabox}{Titulo do Box}\nTexto do Box\n blablabla\n\n\\end{tubainabox}", result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cantDealWithEmptyTitle(){
		new BoxTag().parse(createBoxChunk(null, "Texto do Box\n blablabla\n"));
	}
	
	private BoxChunk createBoxChunk(String title, String text) {
		BoxChunk boxChunk = new BoxChunk(title, Arrays.<Chunk>asList(new ParagraphChunk(text)));
		return boxChunk;
	}

}
