package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.TableChunk;
import br.com.caelum.tubaina.parser.Tag;

public class TableTag implements Tag<TableChunk> {

	public String parse(TableChunk chunk) {
		String result = "";
		String options = chunk.getTitle();
		if (options != null && options.trim().length() > 0)
			result += "<h3>" + options + "</h3>";
		result += "<table";
		if (!chunk.hasNoBorder())
			result += " border=1";
		//XXX: null
		result += ">" + chunk.getContent() + "</table>";
		return result;
	}

}
