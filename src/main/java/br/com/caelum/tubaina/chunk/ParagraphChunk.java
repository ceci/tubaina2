package br.com.caelum.tubaina.chunk;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

public class ParagraphChunk implements Chunk{

	private String content;
	@Inject
	private Tag<ParagraphChunk> tag;

	public String asString() {
		return tag.parse(this);
	}	
	public ParagraphChunk(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
