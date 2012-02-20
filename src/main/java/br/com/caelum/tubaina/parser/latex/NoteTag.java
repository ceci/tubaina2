package br.com.caelum.tubaina.parser.latex;

import br.com.caelum.tubaina.chunk.NoteChunk;
import br.com.caelum.tubaina.parser.Tag;

public class NoteTag implements Tag<NoteChunk> {

	public String parse(NoteChunk chunk) {
		return "\\begin{tubainabox}{Instructor's note}\n" +
				chunk.getContent() + "\n" +
				"\\end{tubainabox}";
	}
}
