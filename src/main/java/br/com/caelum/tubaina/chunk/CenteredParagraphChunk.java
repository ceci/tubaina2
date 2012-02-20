package br.com.caelum.tubaina.chunk;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

public class CenteredParagraphChunk implements Chunk {

	private final String content;
	@Inject
	private Tag<CenteredParagraphChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public CenteredParagraphChunk(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
