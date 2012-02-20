package br.com.caelum.tubaina.chunk;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

public class MockedChunk implements Chunk {

	private String content;
	@Inject
	private Tag<MockedChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public MockedChunk(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

}
