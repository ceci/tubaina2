package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.chunk.BoxChunk;
import br.com.caelum.tubaina.parser.Tag;

public class BoxTag implements Tag<BoxChunk> {

	public String parse(BoxChunk chunk) {
		return "\\begin{tubainabox}{" + chunk.getTitle() + "}\n" + 
				chunk.getContent() + "\n" +
				"\\end{tubainabox}";
	}
}
