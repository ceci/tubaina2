package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ListTag implements Tag<ListChunk> {

	public String parse(ListChunk chunk) {
		String content = chunk.getContent();
		switch (chunk.getType()) {
		case NUMBER:
			return "<ol>" + content + "</ol>";
		case LETTER:
			return "<ol class=\"letter\">" + content + "</ol>";
		case ROMAN:
			return "<ol class=\"roman\">" + content + "</ol>";
		default:
			return "<ul>" + content.trim() + "</ul>";
		}
	}
}
