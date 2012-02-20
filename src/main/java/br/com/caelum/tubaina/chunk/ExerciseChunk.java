package br.com.caelum.tubaina.chunk;

import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

import com.google.inject.Inject;

public class ExerciseChunk implements CompositeChunk {

	private static int COUNT = 1;
	private List<Chunk> body;
	private int id;
	
	@Inject
	private Tag<ExerciseChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public ExerciseChunk(List<Chunk> body) {
		this.body = body;
		this.id = COUNT++;
	}

	public String getContent() {
		String content = "";
		for (Chunk c : body) {
			content += c.asString();
		}
		return content;
	}
	
	public static int getExerciseCount() {
		return COUNT;
	}
}
