package br.com.caelum.tubaina.parser.latex;

import java.util.List;

import br.com.caelum.tubaina.parser.RegexTag;

public class LatexParser {

    public static final int MAX_LINE_LENGTH = 93;

    private final List<RegexTag> tags;

    private boolean showNotes;
    private final boolean noAnswer;

    public LatexParser(List<RegexTag> tags, boolean showNotes, boolean noAnswer) {
        this.tags = tags;
        this.showNotes = showNotes;
        this.noAnswer = noAnswer;
    }

    public LatexParser(List<RegexTag> tags) {
        this.tags = tags;
        this.showNotes = false;
        this.noAnswer = false;
    }

    public String parse(String string) {
        // TODO: remove eventual $1, $2 from the string so as not to be
        // interpreted

        string = new EscapeTag().parse(string);
        for (RegexTag tag : tags) {
            string = tag.parse(string);
        }
        return string;
    }
}