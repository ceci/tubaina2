package br.com.caelum.tubaina.format.html;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.caelum.tubaina.Book;
import br.com.caelum.tubaina.Chapter;
import br.com.caelum.tubaina.Section;
import br.com.caelum.tubaina.TubainaException;
import br.com.caelum.tubaina.io.TubainaDir;
import br.com.caelum.tubaina.io.TubainaHtmlIO;
import br.com.caelum.tubaina.parser.Parser;
import br.com.caelum.tubaina.template.FreemarkerProcessor;
import br.com.caelum.tubaina.util.Utilities;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

public class SingleHtmlGenerator implements Generator {

	private static final Logger LOG = Logger.getLogger(SingleHtmlGenerator.class);

	private Parser parser;
	private File templateDir;
	private Configuration cfg;


	public SingleHtmlGenerator(Parser parser, File templateDir) {
		this.parser = parser;
		this.templateDir = new File(templateDir, "singlehtml/");
		configureFreemarker();
	}

	public void generate(Book book, File outputDir) throws IOException {
		StringBuffer bookContent = generateHeader(book);
		TubainaDir bookRoot = new TubainaHtmlIO(templateDir).createTubainaDir(outputDir, book);

		for (Chapter c : book.getChapters()) {
			StringBuffer chapterContent = generateChapter(book, c);
			bookContent.append(chapterContent);
			if(!c.getResources().isEmpty()) {
				bookRoot.cd(Utilities.toDirectoryName(null, c.getTitle()))
						.writeResources(c.getResources());
			}
		}
		bookContent.append(generateFooter());

		bookRoot.writeIndex(bookContent);
	}
	
	private StringBuffer generateHeader(Book book) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("booktitle", book.getName());
		return new FreemarkerProcessor(cfg).process(map, "book-header.ftl");
	}

	private StringBuffer generateChapter(Book book, Chapter chapter) {
		StringBuffer chapterContent = new ChapterToString(parser, cfg, null).generateSingleHtmlChapter(book, chapter);
		for (Section section : chapter.getSections()) {
			if (section.getTitle() != null) { // intro
				StringBuffer sectionContent = new SectionToString(parser, cfg, null).generateSingleHtmlSection(section);
				chapterContent.append(sectionContent);
			}
		}
		return fixPaths(chapter, chapterContent);
	}

	private StringBuffer fixPaths(Chapter chapter, StringBuffer chapterContent) {
		String chapterName = Utilities.toDirectoryName(null, chapter.getTitle());
		return new StringBuffer(chapterContent.toString().replace("$$RELATIVE$$", chapterName));
	}
	
	private StringBuffer generateFooter() {
		return new FreemarkerProcessor(cfg).process(new HashMap<String, Object>(), "book-footer.ftl");
	}
	
	private void configureFreemarker() {
		cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(templateDir);
		} catch (IOException e) {
			throw new TubainaException("Couldn't load freemarker template for Single HTML mode", e);
		}
		cfg.setObjectWrapper(new BeansWrapper());
	}
}
