package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.IndexChunk;
import br.com.caelum.tubaina.parser.Tag;

public class IndexTag implements Tag<IndexChunk> {

	public String parse(IndexChunk chunk) {
		return "\n<a id=\"" + chunk.getName() + "\"></a>\n";
	}

}
