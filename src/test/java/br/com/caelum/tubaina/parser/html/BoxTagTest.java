package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.BoxChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.format.html.BoxTag;

public class BoxTagTest {
	
	@Test
	public void testBox() {
		BoxTag tag = new BoxTag();
		String result = tag.parse(createBoxChunk("Texto do Box", "Titulo do Box"));
		Assert.assertEquals("<div class=\"box\"><h4>Titulo do Box</h4>\nTexto do Box</div>", result);
	}
	
	@Test
	public void testBoxWithMultilineContent() {
		BoxTag tag = new BoxTag();
		String result = tag.parse(createBoxChunk("Texto do Box\n blablabla\n", "Titulo do Box"));
		Assert.assertEquals("<div class=\"box\"><h4>Titulo do Box</h4>\nTexto do Box\n blablabla</div>", result);
	}

	private BoxChunk createBoxChunk(String text, String title) {
		BoxChunk boxChunk = new BoxChunk(title, Arrays.<Chunk>asList(new ParagraphChunk(text)));
		return boxChunk;
	}
	
}
