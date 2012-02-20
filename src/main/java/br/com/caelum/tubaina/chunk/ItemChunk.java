package br.com.caelum.tubaina.chunk;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

public class ItemChunk implements Chunk {

	private List<Chunk> body;
	@Inject
	private Tag<ItemChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public ItemChunk(List<Chunk> body) {
		this.body = body;
	}

	public String getContent() {
		String content = "";
		for (Chunk c : body) {
			content += c.asString();
		}
		return content;
	}

}
