package br.com.caelum.tubaina.parser.html;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.format.html.BoxTag;

public class BoxTagTest {
	
	@Test
	public void testBox() {
		BoxTag tag = new BoxTag();
		String result = tag.parse("Texto do Box", "Titulo do Box");
		Assert.assertEquals("<div class=\"box\"><h4>Titulo do Box</h4>\nTexto do Box</div>", result);
	}
	
	@Test
	public void testBoxWithMultilineContent() {
		BoxTag tag = new BoxTag();
		String result = tag.parse("Texto do Box\n blablabla\n", "Titulo do Box");
		Assert.assertEquals("<div class=\"box\"><h4>Titulo do Box</h4>\nTexto do Box\n blablabla</div>", result);
	}
	
}
