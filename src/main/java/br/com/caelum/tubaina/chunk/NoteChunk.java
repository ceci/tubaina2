package br.com.caelum.tubaina.chunk;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

public class NoteChunk implements CompositeChunk {

	private final List<Chunk> body;
	@Inject
	private Tag<NoteChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public NoteChunk(List<Chunk> body) {
		this.body = body;
	}

	public String getContent() {
		String content =  "" + '\n';
		for (Chunk c : body) {
			content += c.asString();
		}
		return content;
	}

}
