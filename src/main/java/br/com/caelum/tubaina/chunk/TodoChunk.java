package br.com.caelum.tubaina.chunk;


public class TodoChunk extends AbstractChunk<TodoChunk> {

	private String content;

	public TodoChunk(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

}
