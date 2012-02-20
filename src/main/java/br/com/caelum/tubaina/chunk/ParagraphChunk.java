package br.com.caelum.tubaina.chunk;


public class ParagraphChunk extends AbstractChunk<ParagraphChunk>{

	private String content;

	public ParagraphChunk(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}
