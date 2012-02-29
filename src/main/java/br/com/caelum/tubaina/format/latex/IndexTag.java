package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.IndexChunk;
import br.com.caelum.tubaina.parser.Tag;

public class IndexTag implements Tag<IndexChunk> {

	public String parse(IndexChunk chunk) {
		return "\n\\index{" + chunk.getName() + "}\n";
	}

}
