package br.com.caelum.tubaina.chunk;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

public class XmlChunk implements Chunk {
	private String content;
	private final String options;
	
	@Inject
	private Tag<XmlChunk> tag;

	public String asString() {
		return tag.parse(this);
	}

	public XmlChunk(String options, String content) {
		this.options = options;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public String getOptions() {
		return this.options;
	}

}
