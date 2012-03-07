package br.com.caelum.tubaina.builder;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.tubaina.Book;
import br.com.caelum.tubaina.parser.TubainaParser;

public class BookBuilder {

	private final String name;
	
	private final List<Reader> readers = new ArrayList<Reader>();
	
	public BookBuilder(String name) {
		this.name = name;
	}

	public void add(Reader fileReader) {
		this.readers.add(fileReader);
	}

	public void addAll(List<Reader> readers) {
		this.readers.addAll(readers);
	}
	
	public Book build() {
		return build(false);
	}
	
	public Book build(boolean showNotes) {
		String[] strings = new String[readers.size()];
		for (int i = 0; i < readers.size(); i++) {
			String content = new Scanner(readers.get(i)).useDelimiter("$$").next();
			strings[i] = content;
		}
		return new TubainaParser(name, showNotes).generate(strings);
	}
}
