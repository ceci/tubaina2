package br.com.caelum.tubaina.chunk;

import br.com.caelum.tubaina.Chapter;
import br.com.caelum.tubaina.TubainaBuilder;
import br.com.caelum.tubaina.TubainaException;
import br.com.caelum.tubaina.util.Utilities;


public class CodeChunk extends AbstractChunk<CodeChunk> {

	private String content;
	private final String options;

	public CodeChunk(String content, String options) {
		int maxLineLenght = Utilities.maxLineLength(content) - Utilities.getMinIndent(content); 
		if(maxLineLenght >TubainaBuilder.MAX_LINE_LENGTH)
			throw new TubainaException ("Chapter " + Chapter.getChaptersCount() + 
										"  -  Code has " + maxLineLenght + " columns:\n\n" + content);
		this.content = content;
		this.options = options;
	}

	public String getContent() {
		return this.content;
	}

	public String getOptions() {
		return options;
	}

}
