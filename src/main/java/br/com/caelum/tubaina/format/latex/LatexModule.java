package br.com.caelum.tubaina.format.latex;

import br.com.caelum.tubaina.Chunk;
import br.com.caelum.tubaina.CompositeChunk;
import br.com.caelum.tubaina.chunk.AnswerChunk;
import br.com.caelum.tubaina.chunk.BoxChunk;
import br.com.caelum.tubaina.chunk.CenteredParagraphChunk;
import br.com.caelum.tubaina.chunk.CodeChunk;
import br.com.caelum.tubaina.chunk.ExerciseChunk;
import br.com.caelum.tubaina.chunk.ImageChunk;
import br.com.caelum.tubaina.chunk.ItemChunk;
import br.com.caelum.tubaina.chunk.ListChunk;
import br.com.caelum.tubaina.chunk.NoteChunk;
import br.com.caelum.tubaina.chunk.ParagraphChunk;
import br.com.caelum.tubaina.chunk.QuestionChunk;
import br.com.caelum.tubaina.chunk.TableChunk;
import br.com.caelum.tubaina.chunk.TableColumnChunk;
import br.com.caelum.tubaina.chunk.TableRowChunk;
import br.com.caelum.tubaina.parser.Indentator;
import br.com.caelum.tubaina.parser.SimpleIndentator;
import br.com.caelum.tubaina.parser.Tag;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;

public class LatexModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Tag<AnswerChunk>>() {}).to(AnswerTag.class);
		bind(new TypeLiteral<Tag<BoxChunk>>() {}).to(BoxTag.class);
		bind(new TypeLiteral<Tag<CenteredParagraphChunk>>() {}).to(CenteredParagraphTag.class);
		bind(new TypeLiteral<Tag<CodeChunk>>() {}).to(CodeTag.class);
		bind(new TypeLiteral<Tag<ExerciseChunk>>() {}).to(ExerciseTag.class);
		bind(new TypeLiteral<Tag<ImageChunk>>() {}).to(ImageTag.class);
		bind(new TypeLiteral<Tag<ItemChunk>>() {}).to(ItemTag.class);
		bind(new TypeLiteral<Tag<ListChunk>>() {}).to(ListTag.class);
		bind(new TypeLiteral<Tag<NoteChunk>>() {}).to(NoteTag.class);
		bind(new TypeLiteral<Tag<ParagraphChunk>>() {}).to(ParagraphTag.class);
		bind(new TypeLiteral<Tag<QuestionChunk>>() {}).to(QuestionTag.class);
		bind(new TypeLiteral<Tag<TableColumnChunk>>() {}).to(TableColumnTag.class);
		bind(new TypeLiteral<Tag<TableRowChunk>>() {}).to(TableRowTag.class);
		bind(new TypeLiteral<Tag<TableChunk>>() {}).to(TableTag.class);
		
		bind(Indentator.class).to(SimpleIndentator.class);
	}

	public void inject(Chunk chunk) {
		Injector injector = Guice.createInjector(new LatexModule());
		injector.injectMembers(chunk);
		if (chunk instanceof CompositeChunk<?>) {
			CompositeChunk<?> composite = (CompositeChunk<?>) chunk;
			for (Chunk other : composite.getBody()) {
				inject(other);
			}
		}
	}
}
