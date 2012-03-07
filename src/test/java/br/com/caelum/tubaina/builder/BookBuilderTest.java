package br.com.caelum.tubaina.builder;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.tubaina.Book;
import br.com.caelum.tubaina.Chapter;
import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.Section;
import br.com.caelum.tubaina.TubainaException;
import br.com.caelum.tubaina.chunk.BoxChunk;
import br.com.caelum.tubaina.chunk.CenteredParagraphChunk;
import br.com.caelum.tubaina.chunk.CodeChunk;
import br.com.caelum.tubaina.chunk.ImageChunk;
import br.com.caelum.tubaina.chunk.IntroductionChunk;
import br.com.caelum.tubaina.chunk.ItemChunk;
import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.TableChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;
import br.com.caelum.tubaina.parser.Tag;
import br.com.caelum.tubaina.resources.ResourceLocator;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;

public class BookBuilderTest {


	private MockModule module;

	private final class MockModule extends AbstractModule {
		@Override
		protected void configure() {
			Tag tag = new Tag<CompositeChunk>() {
				public String parse(CompositeChunk chunk) {
					return chunk.getContent();
				}
				
			};
			bind(new TypeLiteral<Tag<CenteredParagraphChunk>>() {}).toInstance(new Tag<CenteredParagraphChunk>() {
				public String parse(CenteredParagraphChunk chunk) {
					return chunk.getContent();
				}
				
			});
			bind(new TypeLiteral<Tag<ParagraphChunk>>() {}).toInstance(new Tag<ParagraphChunk>() {
				public String parse(ParagraphChunk chunk) {
					return chunk.getContent();
				}
				
			});
			bind(new TypeLiteral<Tag<ImageChunk>>() {}).toInstance(new Tag<ImageChunk>() {
				public String parse(ImageChunk chunk) {
					return chunk.getPath();
				}
				
			});
			bind(new TypeLiteral<Tag<CodeChunk>>() {}).toInstance(new Tag<CodeChunk>() {
				public String parse(CodeChunk chunk) {
					return chunk.getContent();
				}
				
			});
			bind(new TypeLiteral<Tag<IntroductionChunk>>() {}).toInstance(tag);
			bind(new TypeLiteral<Tag<BoxChunk>>() {}).toInstance(tag);
			bind(new TypeLiteral<Tag<ListChunk>>() {}).toInstance(tag);
			bind(new TypeLiteral<Tag<ItemChunk>>() {}).toInstance(tag);
			bind(new TypeLiteral<Tag<TableChunk>>() {}).toInstance(tag);
			bind(new TypeLiteral<Tag<TableRowChunk>>() {}).toInstance(tag);
			bind(new TypeLiteral<Tag<TableColumnChunk>>() {}).toInstance(tag);
		}

		public void inject(Book book) {
			Injector injector = Guice.createInjector(new MockModule());
			List<Chapter> chapters = book.getChapters();
			for (Chapter chapter : chapters) {
				inject(injector, (Chunk) new Mirror().on(chapter).get().field("introduction"));
				for (Section section : chapter.getSections()) {
					for (Chunk chunk : section.getChunks()) {
						inject(injector, chunk);
					}
				}
			}
		}

		private void inject(Injector injector, Chunk chunk) {
			injector.injectMembers(chunk);
			if (chunk instanceof CompositeChunk<?>) {
				CompositeChunk<?> composite = (CompositeChunk<?>) chunk;
				for (Chunk other : composite.getBody()) {
					inject(injector, other);
				}
			}
		}
	}

	@Before
	public void setUp() {
		module = new MockModule();
		ResourceLocator.initialize(".");
	}

	@Test
	public void testBuildSimpleBook() {

		BookBuilder builder = new BookBuilder("livro");

		builder.add(new StringReader("[chapter     O que é java?   ]\n" + "texto da seção\n"
				+ "[section Primeira seção]\n" + "texto da prim seção\n" + "[section Segunda seção]\n"
				+ "texto da segunda seção\n\n"));

		builder.add(new StringReader("[chapter Introdução]\n" + "Algum texto de introdução\n"));
		Book book = builder.build();
		module.inject(book);
		Assert.assertEquals("livro", book.getName());

		List<Chapter> chapters = book.getChapters();

		Assert.assertEquals(2, chapters.size());
		List<Section> sections1 = chapters.get(0).getSections();

		Assert.assertEquals("O que é java?", chapters.get(0).getTitle());
		Assert.assertEquals(2, sections1.size());

		Assert.assertEquals("Primeira seção", sections1.get(0).getTitle());
		Assert.assertEquals("texto da prim seção", sections1.get(0).getChunks().get(0).asString());

		Assert.assertEquals("Segunda seção", sections1.get(1).getTitle());
		Assert.assertEquals("texto da segunda seção", sections1.get(1).getChunks().get(0).asString());

		Assert.assertEquals("Algum texto de introdução", chapters.get(1).getIntroduction());

	}

	@Test
	public void testChapterWithoutSections() {
		List<Chapter> chapters = getChapters("[chapter     O que é java?   ]");

		Assert.assertEquals(1, chapters.size());
		Assert.assertEquals(0, chapters.get(0).getSections().size());
		Assert.assertEquals("O que é java?", chapters.get(0).getTitle());
	}

	@Test
	public void testChapterWithoutSectionsAndWithIntroduction() {
		List<Chapter> chapters = getChapters("[chapter     O que é java?   ]\n" + "texto da introdução");

		List<Section> sections = chapters.get(0).getSections();

		Assert.assertEquals(1, chapters.size());
		Assert.assertEquals("O que é java?", chapters.get(0).getTitle());
		Assert.assertEquals(0, sections.size());
		Assert.assertEquals("texto da introdução", chapters.get(0).getIntroduction());
	}

	@Test
	public void testChapterWithSectionsAndWithIntroduction() {
		List<Chapter> chapters = getChapters("[chapter     O que é java?   ]\n" + "texto da introdução\n"
				+ "[section Primeira seção]\n" + "texto da prim seção\n" + "[section Segunda seção]\n"
				+ "texto da segunda seção\n\n");

		Assert.assertEquals(1, chapters.size());
		List<Section> sections = chapters.get(0).getSections();
		Assert.assertEquals("O que é java?", chapters.get(0).getTitle());
		Assert.assertEquals(2, sections.size());

		Assert.assertEquals("texto da introdução", chapters.get(0).getIntroduction());

		Assert.assertEquals("Primeira seção", sections.get(0).getTitle());
		Assert.assertEquals("texto da prim seção", sections.get(0).getChunks().get(0).asString());

		Assert.assertEquals("Segunda seção", sections.get(1).getTitle());
		Assert.assertEquals("texto da segunda seção", sections.get(1).getChunks().get(0).asString());
	}

	@Test
	public void testChapterWithSectionsAndWithoutIntroduction() {
		List<Chapter> chapters = getChapters("[chapter     O que é java?   ]\n" + "[section Primeira seção]\n"
				+ "texto da prim seção\n" + "[section Segunda seção]\n" + "texto da segunda seção\n\n");

		Assert.assertEquals(1, chapters.size());
		List<Section> sections = chapters.get(0).getSections();
		Assert.assertEquals("O que é java?", chapters.get(0).getTitle());
		Assert.assertEquals(2, sections.size());

		Assert.assertEquals("Primeira seção", sections.get(0).getTitle());
		Assert.assertEquals("texto da prim seção", sections.get(0).getChunks().get(0).asString());

		Assert.assertEquals("Segunda seção", sections.get(1).getTitle());
		Assert.assertEquals("texto da segunda seção", sections.get(1).getChunks().get(0).asString());
	}

	@Test(expected=TubainaException.class)
	public void testMultiChaptersMustThrowAnException() {
		getChapters("[chapter     O que é java?   ]\n" + "texto da introdução\n"
				+ "[section Primeira seção]\n" + "texto da prim seção\n" + "[section Segunda seção]\n"
				+ "texto da segunda seção\n\n" + "[chapter Introdução]\n" + "Algum texto de introdução\n");

	}

	private List<Chapter> getChapters(final String string) {
		BookBuilder builder = new BookBuilder("livro");
		builder.add(new StringReader(string));
		Book b = builder.build();
		module.inject(b);
		List<Chapter> chapters = b.getChapters();
		return chapters;
	}

	@Test
	public void testParagraphChunk() {
		List<Chapter> chapters = getChapters("[chapter  Capítulo cheio de Chunks]\n" + "[section secao]"
				+ "\n\nAlgum texto de parágrafo");

		List<Chunk> chunks = chapters.get(0).getSections().get(0).getChunks();
		Assert.assertEquals(1, chunks.size());

		Assert.assertEquals(ParagraphChunk.class, chunks.get(0).getClass());
		Assert.assertEquals("Algum texto de parágrafo", chunks.get(0).asString());
	}

	@Test
	public void testBoxChunk() throws Exception {
		List<Chapter> chapters = getChapters("[chapter  Capítulo cheio de Chunks]\n" + "[section secao]"
				+ "\n\n[box Alguma coisa]\n" + "Algum corpo de texto\n\n" + "[/box]\n\n");

		List<Chunk> chunks = chapters.get(0).getSections().get(0).getChunks();
		Assert.assertEquals(1, chunks.size());

		Assert.assertEquals(BoxChunk.class, chunks.get(0).getClass());
		Assert.assertEquals("Algum corpo de texto", chunks.get(0).asString());

		Field field = BoxChunk.class.getDeclaredField("title");
		field.setAccessible(true);

		String title = (String) field.get(chunks.get(0));
		Assert.assertEquals("Alguma coisa", title);
	}

	@Test
	public void testCodeChunk() {
		List<Chapter> chapters = getChapters("[chapter  Capítulo cheio de Chunks]\n" + "[section secao]"
				+ "\n\n[code]\n" + "Algum corpo de texto\n" + "que é preformatado\n" + "[/code]\n\n");

		List<Chunk> chunks = chapters.get(0).getSections().get(0).getChunks();
		Assert.assertEquals(1, chunks.size());

		Assert.assertEquals(CodeChunk.class, chunks.get(0).getClass());
		Assert.assertEquals("\nAlgum corpo de texto\nque é preformatado\n", chunks.get(0).asString());
	}

	@Test
	public void testImageChunk() throws Exception {
		List<Chapter> chapters = getChapters("[chapter  Capítulo cheio de Chunks]\n" + "[section secao]"
				+ "\n\n[img   src/test/resources/baseJpgImage.jpg w=30 \"Descrição\"   ]\n\n");

		List<Chunk> chunks = chapters.get(0).getSections().get(0).getChunks();
		Assert.assertEquals(1, chunks.size());

		Assert.assertEquals(ImageChunk.class, chunks.get(0).getClass());
		Assert.assertEquals("src/test/resources/baseJpgImage.jpg", chunks.get(0).asString());

		Field field = ImageChunk.class.getDeclaredField("width");
		field.setAccessible(true);

		int width = field.getInt(chunks.get(0));
		Assert.assertEquals(627, width);

		Field field2 = ImageChunk.class.getDeclaredField("options");
		field2.setAccessible(true);

		String options = (String) field2.get(chunks.get(0));
		Assert.assertEquals("w=30 \"Descrição\"", options);
	}

	@Test
	public void testListChunk() {
		List<Chapter> chapters = getChapters("[chapter  Capítulo cheio de Chunks]\n" + "[section secao]"
				+ "\n\n[list]* uma lista\n\n* com alguns itens\n\n * pra ter certeza que funciona[/list]\n\n");

		List<Chunk> chunks = chapters.get(0).getSections().get(0).getChunks();
		Assert.assertEquals(1, chunks.size());

		Assert.assertEquals(ListChunk.class, chunks.get(0).getClass());
		Assert.assertEquals("uma listacom alguns itenspra ter certeza que funciona", chunks.get(0).asString());
	}
	
	@Test
	public void testTableChunk() {
		List<Chapter> chapters = getChapters("[chapter  Capítulo com tabela]\n" + "[section secao]"
				+ "\n\n[table][row]\n[col]uma tabela[/col]\n[col]com várias colunas[/col]\n[/row]\n"
				+ "[row]\n[col]e várias[/col]\n[col]linhas também[/col]\n[/row]\n[/table]\n");

		List<Chunk> chunks = chapters.get(0).getSections().get(0).getChunks();
		Assert.assertEquals(1, chunks.size());

		Assert.assertEquals(TableChunk.class, chunks.get(0).getClass());
		Assert.assertEquals("uma tabelacom várias colunase váriaslinhas também", chunks.get(0).asString());
	}
	
	@Test
	public void testCenteredParagraphChunk() {
		List<Chapter> chapters = getChapters("[chapter  Capítulo com texto centralizado]\n" + "[section secao]"
				+ "\n\n[center]Algum texto centralizado\n\nCom várias linhas[/center]\n\n");

		List<Chunk> chunks = chapters.get(0).getSections().get(0).getChunks();
		Assert.assertEquals(1, chunks.size());
		
		Assert.assertEquals(CenteredParagraphChunk.class, chunks.get(0).getClass());
		Assert.assertEquals("Algum texto centralizado\n\nCom várias linhas", chunks.get(0).asString());
	}

	@Test
	public void testChunkTypesTogether() throws Exception {
		List<Chapter> chapters = getChapters("[chapter  Capítulo cheio de Chunks]\n" + "[section secao]"
				+ "Um chunk de Paragrafo normal\n" + "Com um monte de coisas escritas\n" + "Em várias linhas\n\n"
				+ "[code java] Agora um chunk com código java\n" + "Também multiline\n" + "\n\n" + "[/code]\n"
				+ "Mais algum texto que deveria ser chunk de parágrafo\n" + "[box Um chunk de box]\n"
				+ "Algo escrito dentro dele\n\n" + "Com pseudo-parágrafos [/box]\n\n"
				+ "[code] Um monte de código genérico \n[/code][list]* uma lista\n\n*"
				+ " com alguns itens\n\n * pra ter certeza que funciona[/list]\n\n"
				+ "[table][row]\n[col]uma tabela[/col]\n[col]com várias colunas[/col]\n[/row]\n"
				+ "[row]\n[col]e várias[/col]\n[col]linhas também[/col]\n[/row]\n[/table]\n\n"
				+ "[center]Algum texto centralizado\n\nCom várias linhas[/center]\n\n");

		Assert.assertEquals(1, chapters.size());

		List<Section> sections = chapters.get(0).getSections();

		Assert.assertEquals("Capítulo cheio de Chunks", chapters.get(0).getTitle());
		Assert.assertEquals(1, sections.size());

		List<Chunk> chunks = sections.get(0).getChunks();

		Assert.assertEquals(8, chunks.size());

		// Primeiro Chunk
		Assert.assertEquals(ParagraphChunk.class, chunks.get(0).getClass());
		Assert.assertEquals(
				"Um chunk de Paragrafo normal\n" + "Com um monte de coisas escritas\n" + "Em várias linhas", chunks
						.get(0).asString());

		// Segundo chunk
		Assert.assertEquals(CodeChunk.class, chunks.get(1).getClass());
		Assert.assertEquals("Agora um chunk com código java\n" + "Também multiline", chunks.get(1).asString()
				.trim());

		// Terceiro Chunk
		Assert.assertEquals(ParagraphChunk.class, chunks.get(2).getClass());
		Assert.assertEquals("Mais algum texto que deveria ser chunk de parágrafo", chunks.get(2).asString());

		// Quarto Chunk
		Assert.assertEquals(BoxChunk.class, chunks.get(3).getClass());
		Assert.assertEquals("Algo escrito dentro dele" + "Com pseudo-parágrafos", chunks.get(3).asString());

		Field field = BoxChunk.class.getDeclaredField("title");
		field.setAccessible(true);

		String title = (String) field.get(chunks.get(3));
		Assert.assertEquals("Um chunk de box", title);

		// Quinto Chunk
		Assert.assertEquals(CodeChunk.class, chunks.get(4).getClass());
		Assert.assertEquals(" Um monte de código genérico \n", chunks.get(4).asString());

		// Sexto Chunk
		Assert.assertEquals(ListChunk.class, chunks.get(5).getClass());
		Assert.assertEquals("uma listacom alguns itenspra ter certeza que funciona", chunks.get(5).asString());
		
		// Sétimo Chunk
		Assert.assertEquals(TableChunk.class, chunks.get(6).getClass());
		Assert.assertEquals("uma tabelacom várias colunase váriaslinhas também", chunks.get(6).asString());
		
		// Oitavo Chunk
		Assert.assertEquals(CenteredParagraphChunk.class, chunks.get(7).getClass());
		Assert.assertEquals("Algum texto centralizado\n\nCom várias linhas", chunks.get(7).asString());
	}

}
