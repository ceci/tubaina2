package br.com.caelum.tubaina.parser.latex;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.tubaina.chunk.CodeChunk;
import br.com.caelum.tubaina.format.latex.CodeTag;
import br.com.caelum.tubaina.parser.SimpleIndentator;


public class CodeTagTest extends AbstractTagTest {

	@Test
	public void testPropertiesCodeTag() throws Exception {
		String options = "properties";
		String code = "blablah blah\n" +
				"#algum comentario\n" +
				"texto=valor\n" +
				"texto:valor\n" +
				"texto valor";
		String output = getContent(new CodeChunk(code, options));
		Assert.assertEquals(CodeTag.BEGIN + "{properties}\n" +
				"blablah blah\n" +
				"#algum comentario\n" +
				"texto=valor\n" +
				"texto:valor\n" +
				"texto valor" + CodeTag.END, output);
	}
	@Test
	public void testPropertiesCodeTagWithEscapes() throws Exception {
		String options = "properties abc";
		String string = "blablah blah\n" +
				"#algum comentario\n" +
				"texto\\=valor=valor\n" +
				"texto\\:valor:valor\n" +
				"texto\\ valor valor\n" +
				"a b\\#fake comentario";
		String output = getContent(new CodeChunk(string, options));
		Assert.assertEquals(CodeTag.BEGIN + "{properties}\n" +
				"blablah blah\n" +
				"#algum comentario\n" +
				"texto\\=valor=valor\n" +
				"texto\\:valor:valor\n" +
				"texto\\ valor valor\n" +
				"a b\\#fake comentario" + CodeTag.END, output);
		
	}
	
	@Test
	public void languageCodeTagIsReturnedInsideMintedEnvironment() throws Exception {
		String code = "public static void main(String[] args) {";
		String options = "java";
		String output = getContent(new CodeChunk(code , options));
		Assert.assertEquals(CodeTag.BEGIN + "{java}\n" +
							code + 
							CodeTag.END, output);
	}
	
	@Test
	public void languageCodeTagShouldInsertLineNumbersWhenOptionContainsSharp(){
		String code = "public static void main(String[] args) {";
		String options = "java #";
		String output = getContent(new CodeChunk(code , options));
		Assert.assertEquals(CodeTag.BEGIN + "[linenos, numbersep=5pt]{java}\n" +
							code + 
							CodeTag.END, output);
	}
	
}
