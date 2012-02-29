package br.com.caelum.tubaina.format.latex;

import com.google.inject.Inject;

import br.com.caelum.tubaina.chunk.CodeChunk;
import br.com.caelum.tubaina.parser.Indentator;
import br.com.caelum.tubaina.parser.Tag;

public class CodeTag implements Tag<CodeChunk> {

	private final Indentator indentator;

	public static final String BEGIN = "{\\begin{flushright} \\begin{minipage}{16.9cm} \\small \n\\begin{minted}";
	public static final String END = "\n\\end{minted}\n\\end{minipage}\\end{flushright}}";

	@Inject
	public CodeTag(Indentator indentator) {
		this.indentator = indentator;
	}

	public String parse(CodeChunk chunk) {
		String options = chunk.getOptions();
		String chosenLanguage = options == null ? "" : options.trim().split(" ")[0].trim();	
		if(chosenLanguage.isEmpty()){
			chosenLanguage = "text";
		}
		String lineNumbers = options.contains("#") ? "[linenos, numbersep=5pt]": "";
		
		String indentedString = this.indentator.indent(chunk.getContent());
		return CodeTag.BEGIN + lineNumbers + "{" + chosenLanguage + "}\n" + indentedString + CodeTag.END;
	}

	
}
