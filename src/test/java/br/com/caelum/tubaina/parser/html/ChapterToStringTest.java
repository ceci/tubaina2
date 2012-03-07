package br.com.caelum.tubaina.parser.html;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.tubaina.Book;
import br.com.caelum.tubaina.Chapter;
import br.com.caelum.tubaina.InjectUtils;
import br.com.caelum.tubaina.TubainaBuilder;
import br.com.caelum.tubaina.builder.BookBuilder;
import br.com.caelum.tubaina.builder.ChapterBuilder;
import br.com.caelum.tubaina.format.html.ChapterToString;
import br.com.caelum.tubaina.format.html.HtmlModule;
import br.com.caelum.tubaina.format.html.HtmlParser;
import br.com.caelum.tubaina.parser.Parser;
import br.com.caelum.tubaina.parser.RegexConfigurator;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

public class ChapterToStringTest {

	private ChapterToString chapterToString;

	private String sectionIdentifier;

	@Before
	public void setUp() throws IOException {
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(TubainaBuilder.DEFAULT_TEMPLATE_DIR, "html/"));
		cfg.setObjectWrapper(new BeansWrapper());

		Parser parser = new HtmlParser(new RegexConfigurator().read("/regex.properties", "/html.properties"), false);
		ArrayList<String> dirTree = new ArrayList<String>();
		dirTree.add("livro");
		dirTree.add("livro/01-capitulo");
		dirTree.add("livro/01-capitulo/01-primeira");
		dirTree.add("livro/01-capitulo/02-segunda");

		chapterToString = new ChapterToString(parser, cfg, dirTree);
		sectionIdentifier = "class=\"indexSection\"";
	}

	private Chapter createChapter(final String introduction, final String chapterText) {
		return new ChapterBuilder("Title", introduction, chapterText).build();
	}

	private int countOccurrences(final String text, final String substring) {
		String[] tokens = text.split(substring);
		return tokens.length - 1;
	}

	@Test
	public void testGenerateChapterWithSections() {
		Chapter c = createChapter("introducao", "[section primeira] conteudo da primeira "
				+ "\n[section segunda] conteudo da segunda");

		Book book = new BookBuilder("meu-livro").build();
		book.getChapters().add(c);
		InjectUtils.inject(book, new HtmlModule());
		String string = chapterToString.generateChapter(book, c, 1, 1).toString();

		Assert.assertEquals(2, countOccurrences(string, sectionIdentifier));
		Assert.assertEquals(1, countOccurrences(string, "href=\"../../livro/01-capitulo/01-primeira/\""));
		Assert.assertEquals(1, countOccurrences(string, "href=\"../../livro/01-capitulo/02-segunda/\""));
		Assert.assertEquals(1, countOccurrences(string, "<span class=\"chapterNumber\">1<"));
	}

	// TODO Este teste não faz mais sentido algum. Modificar.
	@Test
	public void testGenerateChapterWithIntroduction() {
		Chapter c = createChapter("conteudo da secao vazia", "");
		Book book = new BookBuilder("meu-livro").build();
		book.getChapters().add(c);
		InjectUtils.inject(book, new HtmlModule());
		String string = chapterToString.generateChapter(book, c, 2, 1).toString();

		Assert.assertEquals(0, countOccurrences(string, sectionIdentifier));
		Assert.assertEquals(1, countOccurrences(string, "<span class=\"chapterNumber\">2<"));
	}
	
	@Test
	public void testGenerateFlatChapterWithSections() {
		Chapter c = createChapter("introducao", "[section primeira] conteudo da primeira "
				+ "\n[section segunda] conteudo da segunda");

		Book book = new BookBuilder("").build();
		book.getChapters().add(c);
		InjectUtils.inject(book, new HtmlModule());
		String head = chapterToString.generateFlatChapterHead(book, c, 1, 1).toString();
		String tail = chapterToString.generateFlatChapterTail(book, c, 1, 1).toString();
		String string = head + tail;

		Assert.assertEquals(2, countOccurrences(string, sectionIdentifier));
		Assert.assertEquals(1, countOccurrences(string, "href=\"../../livro/01-capitulo/01-primeira\""));
		Assert.assertEquals(1, countOccurrences(string, "href=\"../../livro/01-capitulo/02-segunda\""));
		Assert.assertEquals(1, countOccurrences(string, "<span class=\"chapterNumber\">1<"));
	}

	// TODO Este teste não faz mais sentido algum. Modificar.
	@Test
	public void testGenerateFlatChapterWithIntroduction() {
		Chapter c = createChapter("conteudo da secao vazia", "");
		Book book = new BookBuilder("").build();
		book.getChapters().add(c);
		InjectUtils.inject(book, new HtmlModule());
		String head = chapterToString.generateFlatChapterHead(book, c, 1, 1).toString();
		String tail = chapterToString.generateFlatChapterTail(book, c, 1, 1).toString();
		String string = head + tail;

		Assert.assertEquals(0, countOccurrences(string, sectionIdentifier));
		Assert.assertEquals(1, countOccurrences(string, "<span class=\"chapterNumber\">1<"));
	}


}
