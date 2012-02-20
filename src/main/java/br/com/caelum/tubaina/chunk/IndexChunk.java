package br.com.caelum.tubaina.chunk;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

public class IndexChunk implements Chunk{

	private final String name;
	@Inject
	private Tag<IndexChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public IndexChunk(String name) {
		this.name = name;
		
	}
	public String getName() {
		return name;
	}

}
