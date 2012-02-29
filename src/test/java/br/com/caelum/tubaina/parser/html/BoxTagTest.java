package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.BoxChunk;

public class BoxTagTest extends AbstractTagTest {
	
	@Test
	public void testBox() {
		String result = getContent(new BoxChunk("Titulo do Box", text("Texto do Box")));
		Assert.assertEquals("<div class=\"box\"><h4>Titulo do Box</h4>\n<p>Texto do Box</p></div>", result);
	}
	
	@Test
	public void testBoxWithMultilineContent() {
		String result = getContent(new BoxChunk("Titulo do Box", text("Texto do Box\n blablabla\n")));
		Assert.assertEquals("<div class=\"box\"><h4>Titulo do Box</h4>\n<p>Texto do Box\n blablabla\n</p></div>", result);
	}
	
}
