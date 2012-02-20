package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.ItemChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ItemTag implements Tag<ItemChunk> {

	public String parse(ItemChunk chunk) {
		return "<li>" + chunk.getContent() + "</li>";
	}
}
