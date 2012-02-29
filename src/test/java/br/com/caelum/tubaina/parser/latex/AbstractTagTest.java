package br.com.caelum.tubaina.parser.latex;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.chunk.MockedChunk;
import br.com.caelum.tubaina.format.latex.LatexModule;

public class AbstractTagTest {

	public AbstractTagTest() {
		super();
	}

	protected String getContent(Chunk chunk) {
		new LatexModule().inject(chunk);
		String result = chunk.asString();
		return result;
	}

	protected List<Chunk> text(String text) {
		return Arrays.<Chunk>asList(new MockedChunk(text));
	}

}