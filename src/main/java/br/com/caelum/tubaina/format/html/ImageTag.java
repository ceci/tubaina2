package br.com.caelum.tubaina.format.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

import br.com.caelum.tubaina.chunk.ImageChunk;
import br.com.caelum.tubaina.parser.Tag;

public class ImageTag implements Tag<ImageChunk> {

	// TODO: make it work more gracefully... i.e., eliminate this workaround
	private static final String RELATIVEPATH = "$$RELATIVE$$/";

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

	public String parse(ImageChunk chunk) {
		String imgsrc = FilenameUtils.getName(chunk.getPath());
		String output = "<img src=\"" + RELATIVEPATH + imgsrc + "\"";
		
		// The image is resized when copied
		if (!chunk.getDescription().isEmpty()) {
			output = output + " alt=\"" + chunk.getDescription() + "\" />";
		} else {
			output = output + " alt=\"" + imgsrc + "\" />";
		}
		
		return output;
	}
}
