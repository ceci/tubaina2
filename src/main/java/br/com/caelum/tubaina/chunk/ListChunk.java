package br.com.caelum.tubaina.chunk;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ListChunk implements CompositeChunk {

	
	private List<Chunk> body;
	private ListType type;
	@Inject
	private Tag<ListChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public ListChunk(ListType type, List<Chunk> body) {
		this.body = body;
		this.type = type;
	}

	public String getContent() {
		String content = "";
		for (Chunk c : body) {
			content += c.asString();
		}
		return content;
	}

	public ListType getType() {
		return type;
	}
}
