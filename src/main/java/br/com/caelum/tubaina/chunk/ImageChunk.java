package br.com.caelum.tubaina.chunk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImageChunk extends AbstractChunk<ImageChunk> {

	private final String options;
	private final String path;
	private final int width;

	public ImageChunk(String path, String options, int width) {
		this.options = options;
		this.path = path;
		this.width = width;
	}		

	public String getPath() {
		return path;
	}

	public String getDescription() {
		Pattern description = Pattern.compile("(?s)(?i)\"(.+?)\"");
		Matcher descriptionMatcher = description.matcher(options);
		
		// The image is resized when copied
		if (descriptionMatcher.find()) {
			return descriptionMatcher.group(1);
		} else {
			return "";
		}
	}
	
	public int getActualWidth() {
		return width;
	}
	
	public double getWidthPercentage() {
		Pattern horizontalScale = Pattern.compile("(?s)(?i)w=(\\d+)%?");
		Matcher sMatcher = horizontalScale.matcher(options);

		if (sMatcher.find()) {
			return Double.parseDouble(sMatcher.group(1)) / 100;
		}
		return 0.0;
	}
}
