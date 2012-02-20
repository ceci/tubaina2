package br.com.caelum.tubaina.chunk;

import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;

public class ListChunk extends CompositeChunk<ListChunk> {

	private ListType type;
	
	public ListChunk(List<Chunk> body, ListType type) {
		super(body);
		this.type = type;
	}

	public ListType getType() {
		return type;
	}
}
