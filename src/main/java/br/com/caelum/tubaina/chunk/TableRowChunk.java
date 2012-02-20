package br.com.caelum.tubaina.chunk;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.parser.Tag;

public class TableRowChunk implements CompositeChunk {

	private List<Chunk> cols;

	@Inject
	private Tag<TableRowChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
	
	public TableRowChunk(List<Chunk> cols) {
		this.cols = cols;
	}

	public String getContent() {
		String content = "";
		for (Chunk c : cols) {
			content += c.asString();
		}
		return content;
	}

	public int getNumberOfColumns() {
		int columns = 0;
		for (Chunk c : cols) {
			if (c instanceof TableColumnChunk)
				columns++;
		}
		return columns;
	}

}
