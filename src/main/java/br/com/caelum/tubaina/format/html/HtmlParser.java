package br.com.caelum.tubaina.format.html;

import java.util.List;

import br.com.caelum.tubaina.parser.Parser;
import br.com.caelum.tubaina.parser.RegexTag;
import br.com.caelum.tubaina.util.HtmlSanitizer;

public class HtmlParser implements Parser {

    public static final int MAX_LINE_LENGTH = 80;

    private final HtmlSanitizer sanitizer = new HtmlSanitizer();

    private final List<RegexTag> tags;
    private final boolean noAnswer;

    public HtmlParser(List<RegexTag> tags, boolean noAnswer) {
        this.tags = tags;
		this.noAnswer = noAnswer;
    }

    public String parse(String string) {
        // TODO: remove eventual $1, $2 from the string so as not to be
        // interpreted

        for (RegexTag tag : tags) {
            string = tag.parse(string);
        }
        return string;
    }

}