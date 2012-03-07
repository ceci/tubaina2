package br.com.caelum.tubaina.builder;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
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
import br.com.caelum.tubaina.format.html.HtmlModule;
import br.com.caelum.tubaina.parser.Tag;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;

public class MockModule extends AbstractModule {
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
	
	public void inject(Chunk chunk) {
		Injector injector = Guice.createInjector(this);
		injector.injectMembers(chunk);
		if (chunk instanceof CompositeChunk<?>) {
			CompositeChunk<?> composite = (CompositeChunk<?>) chunk;
			for (Chunk other : composite.getBody()) {
				inject(other);
			}
		}
	}
}