package br.com.caelum.tubaina.parser.html;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.format.html.HtmlModule;

public class AbstractTagTest {

	public AbstractTagTest() {
		super();
	}

	protected String getContent(Chunk chunk) {
		new HtmlModule().inject(chunk);
		String result = chunk.asString();
		return result;
	}

	protected List<Chunk> text(String text) {
		return Arrays.<Chunk>asList(new ParagraphChunk(text));
	}

}