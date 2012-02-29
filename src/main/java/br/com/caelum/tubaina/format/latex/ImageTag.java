package br.com.caelum.tubaina.format.latex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

import br.com.caelum.tubaina.chunk.ImageChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ImageTag implements Tag<ImageChunk> {

	double pageWidth = 175;

	public String parse(ImageChunk chunk) {
//	public String parse(final String path, final String options) {
		String output = "\\begin{figure}[H]\n\\centering\n";

		output = output + "\\includegraphics";

		double width = chunk.getActualWidth();

		if (chunk.getWidthPercentage() != 0.0) {
			output = output + "[width=" + (pageWidth * chunk.getWidthPercentage()) + "mm]";
		} else if (width > pageWidth) {
			output = output + "[width=\\textwidth]";
		} else {
			output = output + "[scale=1]";
		}

		String imgsrc = FilenameUtils.getName(chunk.getPath());
		output = output + "{" + imgsrc + "}\n";

		if (!chunk.getDescription().isEmpty()) {
			output = output + "\n\n\\caption{" + chunk.getDescription() + "}\n\n";
		}

		output = output + "\\end{figure}\n\n";

		return output;
	}

	public Integer getScale(final String string) {
		if (string == null) {
			return null;
		}
		Pattern horizontalScale = Pattern.compile("(?s)(?i)w=(\\d+)%?");
		Matcher sMatcher = horizontalScale.matcher(string);

		if (sMatcher.find()) {
			return Integer.parseInt(sMatcher.group(1));
		}
		return null;
	}
}
