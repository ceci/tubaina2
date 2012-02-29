package br.com.caelum.tubaina.parser.latex;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.chunk.ImageChunk;

public class ImageTagTest extends AbstractTagTest {

	@Test
	public void testFullImageTag() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "w=30 \"Imagem de alguma coisa\"", (int)(52.5/0.3));
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"\\begin{figure}[H]\n\\centering\n" +
				"\\includegraphics[width=52.5mm]{imagem.png}\n" +
				"\n\n\\caption{Imagem de alguma coisa}\n\n" +
				"\\end{figure}\n\n", result);
	}

	@Test
	public void testImageTagWithoutBounds() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "\"Imagem de alguma coisa\"", 42000);
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"\\begin{figure}[H]\n\\centering\n" +
				"\\includegraphics[width=\\textwidth]{imagem.png}\n" +
				"\n\n\\caption{Imagem de alguma coisa}\n\n" +
				"\\end{figure}\n\n", result);
	}

	@Test
	public void testImageTagWithoutDesc() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "w=42", (int)(73.5/0.42));
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"\\begin{figure}[H]\n\\centering\n" +
				"\\includegraphics[width=73.5mm]{imagem.png}\n" +
				"\\end{figure}\n\n", result);
	}
	
	@Test
	public void testImageTagWithPercentageSymbol() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "w=40%", (int)(70.0/0.4));
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"\\begin{figure}[H]\n\\centering\n" +
				"\\includegraphics[width=70.0mm]{imagem.png}\n" +
				"\\end{figure}\n\n", result);
	}
	
	@Test
	public void testImageTagWithoutPercentageSymbol() {
		ImageChunk imageChunk = new ImageChunk("imagem.png", "w=40", (int)(70.0/0.4));
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"\\begin{figure}[H]\n\\centering\n" +
				"\\includegraphics[width=70.0mm]{imagem.png}\n" +
				"\\end{figure}\n\n", result);
	}
	
	@Test
	public void testImageTagWithPath() {
		ImageChunk imageChunk = new ImageChunk("some/path/imagem.png", "w=42", (int)(73.5/0.42));
		String result = getContent(imageChunk);
		Assert.assertEquals(
				"\\begin{figure}[H]\n\\centering\n" +
				"\\includegraphics[width=73.5mm]{imagem.png}\n" +
				"\\end{figure}\n\n", result);
	}
}
