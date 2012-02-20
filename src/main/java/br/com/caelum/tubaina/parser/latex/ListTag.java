package br.com.caelum.tubaina.parser.latex;

import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.chunk.ListType;
import br.com.caelum.tubaina.parser.Tag;

public class ListTag implements Tag<ListChunk> {

	public String parse(ListChunk chunk) {
		ListType type = chunk.getType();
		String listHeader = "\\begin{enumerate}[";
		switch (type) {
		case NUMBER:
			listHeader += "1)";
		case LETTER:
			listHeader += "a)";
		case ROMAN:
			listHeader += "I)";
		case BULLET:
			return "\\begin{itemize}" + chunk.getContent() + "\\end{itemize}";
		}
		return listHeader + "]\n" + chunk.getContent() + "\n\\end{enumerate}";
	}

}
