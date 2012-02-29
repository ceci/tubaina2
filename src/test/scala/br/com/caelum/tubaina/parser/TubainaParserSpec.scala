package br.com.caelum.tubaina.parser

import org.scalatest.FlatSpec
import br.com.caelum.tubaina.Tubaina
import br.com.caelum.tubaina.ParseType
import java.io.File
import org.scalatest.matchers.ShouldMatchers
import br.com.caelum.tubaina.format.html.SingleHtmlGenerator

class TubainaParserSpec extends FlatSpec with ShouldMatchers {

  behavior of "Parser"

  it should "parse something" in {
    val tubainaDoc = Seq("""
      [chapter Um capítulo novo]

      [section Uma nova section]
      **Isso** daqui deveria ::se__r__:: um texto do chapter 1

      [section Uma nova section2]
      asdflasdfklj
      [code]
        blablabla
      [/code]
      [code java]
        String a = "hahaha";
      [/code]
      asdfjalsdkfja
      """, """
      [chapter Outro capítulo]

      [note]
        Isso é uma nota!
        [code]
           Um Sysout de código nesse note
        [/code]
      [/note]
      Introducao do chapter 2
      [section hahaha]
      [box hahaha]
        [code]asdlfajsdflk[/code]
      [/box]
    """)

    val parser = new TubainaParser("livro")
    val parsed = parser.generate(tubainaDoc:_*)
    
    val pars = ParseType.HTML.getParser(new RegexConfigurator, true, false)
    new SingleHtmlGenerator(pars, new File("../tubaina/templates/")).generate(parsed, new File("target"));
  }
  
}