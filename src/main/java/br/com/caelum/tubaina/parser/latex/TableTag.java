package br.com.caelum.tubaina.parser.latex;

import br.com.caelum.tubaina.TubainaException;
import br.com.caelum.tubaina.chunk.TableChunk;
import br.com.caelum.tubaina.parser.Tag;

public class TableTag implements Tag<TableChunk> {

	public String parse(TableChunk chunk) {
		int columns = chunk.getMaxNumberOfColumns();
		boolean hasBorder = !chunk.hasNoBorder();

		if (columns <= 0)
			throw new TubainaException("There are no columns inside table " + chunk.getTitle());
		String tag =  "\\begin{table}[!h]\n\\caption{" + chunk.getTitle() + "}\n\\begin{center}\n";
		if (hasBorder)
			tag += "\\rowcolors[]{1}{gray!30}{gray!15}\n";
		tag += "\\begin{tabularx}{";
		for (int i = 0; i < columns; i++)
			tag += "X";
		tag += "}\n";
		if (hasBorder)
			tag += "\\hline\n";
		tag += chunk.getContent();
		if (hasBorder)
			tag += "\n\\hline";
		tag += "\n\\end{tabularx}\n\\end{center}\n\\end{table}";
		return tag;
	}

}
