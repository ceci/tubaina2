package br.com.caelum.tubaina.chunk;

public class MockedChunk extends AbstractChunk<MockedChunk> {

	private String content;

	public MockedChunk(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

}
