package br.com.caelum.tubaina.builder.replacer;

import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.builder.ChunkSplitter;
import br.com.caelum.tubaina.chunk.NoteChunk;
import br.com.caelum.tubaina.resources.Resource;

public class NoteReplacer extends AbstractReplacer {

	private List<Resource> resources;

	public NoteReplacer(List<Resource> resources) {
		super("note");
		this.resources = resources;
	}

	@Override
	public Chunk createChunk(String options, String content) {
		ChunkSplitter splitter = new ChunkSplitter(resources, "note");
		return new NoteChunk(splitter.splitChunks(content));
	}

}
