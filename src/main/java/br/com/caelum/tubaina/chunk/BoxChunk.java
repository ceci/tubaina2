package br.com.caelum.tubaina.chunk;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

public class BoxChunk implements CompositeChunk {

	private final List<Chunk> body;
	private final String title;
	@Inject
	private Tag<BoxChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public BoxChunk(final String title, final List<Chunk> body) {
		this.title = title;
		this.body = body;
	}

	public String getContent() {
		String content = "";
		for (Chunk c : body) {
			content += c.asString();
		}
		return content;
	}

	public String getTitle() {
		return title;
	}

}
