package br.com.caelum.tubaina;

import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class InjectUtils {

	public static void inject(Book book, Module module) {
		Injector injector = Guice.createInjector(module);
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

	private static void inject(Injector injector, Chunk chunk) {
		injector.injectMembers(chunk);
		if (chunk instanceof CompositeChunk<?>) {
			CompositeChunk<?> composite = (CompositeChunk<?>) chunk;
			for (Chunk other : composite.getBody()) {
				inject(injector, other);
			}
		}
	}
}
