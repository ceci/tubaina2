package br.com.caelum.tubaina.chunk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.parser.Tag;

import com.google.inject.Inject;


public class ImageChunk implements Chunk {

	private final String options;
	private final String path;
	private final int width;
	@Inject
	private Tag<ImageChunk> tag;

	public String asString() {
		return tag.parse(this);
	}
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

}
