package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.ExerciseChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ExerciseTag implements Tag<ExerciseChunk> {

	public String parse(ExerciseChunk chunk) {
		return "<ol class=\"exercise\">" + chunk.getContent() + "</ol>";
	}

}
