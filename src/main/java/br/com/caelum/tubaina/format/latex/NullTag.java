package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.NoteChunk;
import br.com.caelum.tubaina.parser.Tag;

public class NullTag implements Tag<NoteChunk> {

	public String parse(NoteChunk chunk) {
		return "";
	}

}
