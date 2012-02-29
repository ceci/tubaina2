package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.TableRowChunk;
import br.com.caelum.tubaina.parser.Tag;

public class TableRowTag implements Tag<TableRowChunk> {

	public String parse(TableRowChunk chunk) {
		String string = chunk.getContent();
		// Remove the & of the last column (put by the TableColumnTag)
		return string.replaceFirst("& $", "") + "\\\\";
	}

}
