package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.BoxChunk;
import br.com.caelum.tubaina.parser.Tag;

public class BoxTag implements Tag<BoxChunk> {

	public String parse(BoxChunk chunk) {
		return "<div class=\"box\"><h4>" + chunk.getTitle() + "</h4>\n" + chunk.getContent() + "</div>";
	}

}
