package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.parser.Tag;

public class TableColumnTag implements Tag<TableColumnChunk> {

	public String parse(TableColumnChunk chunk) {
		return "<td>" + chunk.getContent() + "</td>";
	}

}
