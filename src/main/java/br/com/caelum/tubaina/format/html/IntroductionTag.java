package br.com.caelum.tubaina.format.html;

import br.com.caelum.tubaina.chunk.IntroductionChunk;
import br.com.caelum.tubaina.parser.Tag;

public class IntroductionTag implements Tag<IntroductionChunk>{

	public String parse(IntroductionChunk chunk) {
		return chunk.getContent();
	}

}
