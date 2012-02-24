package br.com.caelum.tubaina.chunk;


public enum ListType {
	BULLET, NUMBER, LETTER, ROMAN;

	public static ListType from(String options) {
		if (options.contains("number")) {
			return NUMBER;
		} else if (options.contains("letter")) {
			return LETTER;
		} else if (options.contains("roman")) {
			return ROMAN;
		}
		return BULLET;
	}
}
