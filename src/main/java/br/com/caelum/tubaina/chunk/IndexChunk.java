package br.com.caelum.tubaina.chunk;


public class IndexChunk extends AbstractChunk<IndexChunk>{

	private final String name;

	public IndexChunk(String name) {
		this.name = name;
		
	}
	public String getName() {
		return name;
	}

}
