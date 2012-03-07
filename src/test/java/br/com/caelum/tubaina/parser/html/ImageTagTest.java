package br.com.caelum.tubaina.parser.html;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.chunk.ImageChunk;

public class ImageTagTest extends AbstractTagTest {

	@Test
	public void testFullImageTag() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "w=30 \"Imagem de alguma coisa\"", 0);
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"<img src=\"$$RELATIVE$$/imagem.png\" alt=\"Imagem de alguma coisa\" />", result);
	}

	@Test
	public void testImageTagWithoutBounds() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "\"Imagem de alguma coisa\"", 0);
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"<img src=\"$$RELATIVE$$/imagem.png\" alt=\"Imagem de alguma coisa\" />", result);
	}

	@Test
	public void testImageTagWithoutDesc() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "w=42", 0);
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"<img src=\"$$RELATIVE$$/imagem.png\" alt=\"imagem.png\" />", result);
	}
	
	@Test
	public void testImageTagWithPath() {
		ImageChunk imageChunk = new ImageChunk("some/path/imagem.png", "w=42", 0);
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"<img src=\"$$RELATIVE$$/imagem.png\" alt=\"imagem.png\" />", result);
	}
	
	@Test
	public void testImageTagWithPercentageSymbol() {
		ImageChunk imageChunk = new ImageChunk("some/path/imagem.png", "w=50%", 0);
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"<img src=\"$$RELATIVE$$/imagem.png\" alt=\"imagem.png\" />", result);
	}
	
	@Test
	public void testImageTagWithoutPercentageSymbol() {
		ImageChunk imageChunk = new ImageChunk("some/path/imagem.png", "w=50", 0);
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"<img src=\"$$RELATIVE$$/imagem.png\" alt=\"imagem.png\" />", result);
	}
	
}
