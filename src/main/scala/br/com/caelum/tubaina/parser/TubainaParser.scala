package br.com.caelum.tubaina.parser

import util.parsing.combinator._
import java.lang.RuntimeException
import br.com.caelum.tubaina.chunk._
import br.com.caelum.tubaina.Chunk
import scala.collection.JavaConversions._
import br.com.caelum.tubaina.Chapter
import br.com.caelum.tubaina.Section
import br.com.caelum.tubaina.Book
import scala.annotation.varargs
import br.com.caelum.tubaina.TubainaException
import br.com.caelum.tubaina.resources.ResourceLocator
import javax.imageio.ImageIO
import java.io.IOException
import java.io.Reader
import java.util.Scanner
import br.com.caelum.tubaina.resources.Resource
import scala.collection.mutable
import br.com.caelum.tubaina.resources.ImageResource
import br.com.caelum.tubaina.resources.AnswerResource

class TubainaParser(bookName:String, showNotes:Boolean) extends RegexParsers {
	val resources: mutable.Set[Resource]=mutable.Set()
	private def consumeResources = {
		val res = resources.toSeq
		resources.clear()
		res
	} 
	def this(bookName:String) = this(bookName, false)
  var _skip = true
  override def skipWhitespace = _skip
  
  def verbose[T](p:Parser[T]) = new Parser[T] {
    def apply(in:Input) = {
      _skip = false
      val x = p(in)
      _skip = true
      x
    }
  }
  def nonBracket:Parser[String] = "[^\\[\\]]+".r

  def chapter:Parser[Chapter] =
    p("[chapter " ~> nonBracket <~ "]") ~ (content?) ~ (section*) ^^ {
      case name ~ Some(intro) ~ sections => new Chapter(name.trim(), new IntroductionChunk(intro), sections, consumeResources)
      case name ~ None ~ sections => new Chapter(name.trim(), new IntroductionChunk(Seq(new ParagraphChunk(""))), sections, consumeResources)
    }
  
  def section:Parser[Section] =
    p("[section " ~> nonBracket <~ "]") ~ (content?) ^^ {
      case name ~ Some(content) => new Section(name.trim(), content)
      case name ~ None => new Section(name.trim(), Seq())
    }

  def exercises:Parser[ExerciseChunk] = "[exercise]" ~> ((question|todo)+) <~ "[/exercise]" ^^ {
    case questions => new ExerciseChunk(questions)
  }
  
  def question:Parser[QuestionChunk] = "[question]" ~> (content ~ (answer?) | (answer?) ~ content) <~ "[/question]" ^^ {
    case content ~ answer => (content, answer) match {
      case (c:List[Chunk], a:Option[AnswerChunk]) => new QuestionChunk(c ++ a)
      case (Some(a:Chunk), c:List[Chunk]) => new QuestionChunk(a :: c)
      case (None, c:List[Chunk]) => new QuestionChunk(c)
    }
  }
  
  def answer:Parser[AnswerChunk] = "[answer]" ~> content <~ "[/answer]" ^^ {
    case content => 
    	val chunk = new AnswerChunk(content)
    	resources += new AnswerResource(chunk)
    	chunk
  }

//  def bold:Parser[Bold] = "**" ~> paragraph <~ "**" ^^ {p => Bold(p)}
//  def em:Parser[Em] = "::" ~> paragraph <~ "::" ^^ {p => Em(p)}
//  def und:Parser[Und] = "__" ~> paragraph <~ "__" ^^ {p => Und(p)}
//  def mono:Parser[Mono] = "%%" ~> paragraph <~ "%%" ^^ {p => Mono(p)}
  def text:Parser[String] = "((?!\n\n)[^\\[])+".r ^^ {x => x.trim()}

  //  def textElem:Parser[TextElement] = bold | em | und | mono | text
  
  def paragraph:Parser[ParagraphChunk] = (text)  ^^ { x => new ParagraphChunk(x) }

  def code:Parser[CodeChunk] = p("[code " ~> nonBracket <~ "]" | "[code]") ~ verbose(nonBracket) <~ "[/code]" ^^ {
  	case "[code]" ~ content => new CodeChunk(content, "") 
    case opts ~ content => new CodeChunk(content, opts) 
  }

  def box:Parser[BoxChunk] = p("[box " ~> nonBracket <~ "]") ~ content <~ "[/box]" ^^ {
    case title ~ chunks => new BoxChunk(title, chunks)
  }
  
  def note:Parser[NoteChunk] = "[note]" ~> content <~ "[/note]" ^^ (x => new NoteChunk(x))

  def image:Parser[ImageChunk] = "[img " ~> "([^ \t\\]])+".r ~ (nonBracket?) <~ "]" ^^ {
    case path ~ opts => 
      val image = ResourceLocator.getInstance().getFile(path)
		val width = 
		  try {
			ImageIO.read(image).getWidth()
		  } catch {
		    case e:IOException => throw new TubainaException("Image not existant", e)
		    case e:NullPointerException => throw new TubainaException(path + " is not a valid image")
		  }
  	  resources += new ImageResource(image, path)
      new ImageChunk(path, opts.getOrElse("").trim(), width) 
  }
  
  def itemChunk:Parser[Chunk] = ((not(paragraph) ~> elem) | "((?!^\\s*\\*|\\[).)+".r) ^^ {
  	case e:String => new ParagraphChunk(e)
    case e:Chunk => e
  }
  
  def item:Parser[ItemChunk] = "*" ~> itemChunk ~ ((not("^\\s*\\*".r) ~> itemChunk)*) ^^ {
    case e ~ c => new ItemChunk(e :: c)
  }
  
  def list:Parser[ListChunk] = p("[list " ~> nonBracket <~"]" | "[list]") ~ (item+) <~ "[/list]" ^^ {
    case "[list]" ~ x => new ListChunk(x, ListType.BULLET) 
    case opts ~ x => new ListChunk(x, ListType.valueOf(opts.toUpperCase())) 
  }
  
  def col:Parser[TableColumnChunk] = "[col]" ~> content <~ "[/col]" ^^ (x => new TableColumnChunk(x))
    
  def row:Parser[TableRowChunk] = "[row]" ~> ((col|todo)+) <~ "[/row]" ^^ (x => new TableRowChunk(x))
  
  def table:Parser[TableChunk] = p("[table " ~> nonBracket <~"]" | "[table]") ~ ((row|todo)+) <~ "[/table]" ^^ {
    case "[table]" ~ x => new TableChunk("", x) 
    case opts ~ x => new TableChunk(opts, x) 
  }
  
  def center:Parser[CenteredParagraphChunk] = "[center]" ~> ("[^\\[]+".r) <~ "[/center]" ^^ (x => new CenteredParagraphChunk(x))

  def todo:Parser[TodoChunk] = "(?i)\\[todo ".r ~> nonBracket <~ "]" ^^ (x => new TodoChunk(x))
  
  def index:Parser[IndexChunk] = "(?i)\\[index ".r ~> nonBracket <~ "]" ^^ (x => new IndexChunk(x))
  
  def elem:Parser[Chunk] = center.asInstanceOf[Parser[Chunk]] | table | list | image | code | box | note | exercises | todo | index | paragraph 
  
  def content:Parser[Seq[Chunk]] = elem+

  def p(s:Parser[String]) = s

  @varargs
  def generate(toParse:String*):Book = {
	  val chapters = toParse.map { chap =>
      parseAll(chapter, chap) match {
	    case Success(r, q) => r
	    case NoSuccess(message, input) => 
	      throw new TubainaException(message)
	    
	  }
    }
    
    new Book(bookName, chapters, showNotes)
  }
  
}
