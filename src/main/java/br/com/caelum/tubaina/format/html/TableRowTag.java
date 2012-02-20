package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.TableRowChunk;
import br.com.caelum.tubaina.parser.Tag;

public class TableRowTag implements Tag<TableRowChunk> {

	public String parse(TableRowChunk chunk) {
		return "<tr>" + chunk.getContent() + "</tr>";
	}

}
