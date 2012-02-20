package br.com.caelum.tubaina.chunk;

import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;

public class NoteChunk extends CompositeChunk<NoteChunk> {

	public NoteChunk(List<Chunk> body) {
		super(body);
	}

}
