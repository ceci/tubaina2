package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.NoteChunk;
import br.com.caelum.tubaina.parser.Tag;

public class NoteTag implements Tag<NoteChunk> {

	public String parse(NoteChunk chunk) {
		return "<div class=\"note\">" + chunk.getContent().trim() + "</div>";
	}

}
