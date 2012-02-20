package br.com.caelum.tubaina.chunk;

import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

import com.google.inject.Inject;

public class AnswerChunk implements CompositeChunk {

	private static int ANSWER_ID = 0;

	@Inject
	private Tag<AnswerChunk> tag;
	
	private List<Chunk> body;
	private int id;

	public AnswerChunk(List<Chunk> body) {
		this.body = body;
		this.id = ANSWER_ID++;
	}

	public String getContent() {
		String content = "";
		for (Chunk c : body) {
			content += c.asString();
		}
		return content;
	}

	public int getId() {
		return id;
	}

	public String asString() {
		return tag.parse(this);
	}
}
