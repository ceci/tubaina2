package br.com.caelum.tubaina.chunk;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

public class IntroductionChunk implements CompositeChunk {

	private List<Chunk> chunks;
	@Inject
	private Tag<IntroductionChunk> tag;

	public String asString() {
		return tag.parse(this);
	}	
	public IntroductionChunk(List<Chunk> chunks) {
		this.chunks = chunks;
	}
	
	public String getContent() {
		String content = "";
		for (Chunk c : chunks) {
			content += c.asString();
		}
		return content;
	}

}
