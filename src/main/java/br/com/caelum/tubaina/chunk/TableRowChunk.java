package br.com.caelum.tubaina.chunk;

import java.util.List;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;

public class TableRowChunk extends CompositeChunk<TableRowChunk> {

	public TableRowChunk(List<Chunk> body) {
		super(body);
	}

	public int getNumberOfColumns() {
		int columns = 0;
		for (Chunk c : body) {
			if (c instanceof TableColumnChunk)
				columns++;
		}
		return columns;
	}

}
