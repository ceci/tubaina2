package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.ItemChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ItemTag implements Tag<ItemChunk> {

	public String parse(ItemChunk chunk) {
		return "\n\\item{" + chunk.getContent() + "}\n";
	}

}
