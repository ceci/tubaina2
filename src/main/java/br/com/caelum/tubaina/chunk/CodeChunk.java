package br.com.caelum.tubaina.chunk;


public class CodeChunk extends AbstractChunk<CodeChunk> {

	private String content;
	private final String options;

	public CodeChunk(String content, String options) {
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
