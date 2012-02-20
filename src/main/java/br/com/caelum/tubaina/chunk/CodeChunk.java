package br.com.caelum.tubaina.chunk;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

public class CodeChunk implements Chunk {

	private String content;
	private final String options;
	@Inject
	private Tag<CodeChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public CodeChunk(String content, String options) {
		this.content = content;
		this.options = options;
	}

	public String getContent() {
		return this.content;
	}

	public String getOptions() {
		return options;
	}

}
