package br.com.caelum.tubaina.chunk;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

public class TableColumnChunk implements CompositeChunk {

	private List<Chunk> cell;
	@Inject
	private Tag<TableColumnChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	public TableColumnChunk(List<Chunk> cell) {
		this.cell = cell;
	}

	public String getContent() {
		String content = "";
		for (Chunk c : cell) {
			content += c.asString();
		}
		return content;
	}

}
