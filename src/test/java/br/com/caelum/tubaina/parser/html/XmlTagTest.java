package br.com.caelum.tubaina.parser.html;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.tubaina.format.html.XmlTag;
import br.com.caelum.tubaina.parser.SimpleIndentator;

public class XmlTagTest {

	private static final String BEGIN = XmlTag.BEGIN;
	private static final String END = XmlTag.END;
	
	@Test
	public void testSpecialXmlTags() {
		String text = "\n<?xml version=1.0>";
		String result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"texspecial\">&lt;?xml&nbsp;version=1.0&gt;</span>" + END, result);
		
		text = "\n<!DOCTYPE BLAH BLAH BLAH>";
		result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"texspecial\">&lt;!DOCTYPE&nbsp;BLAH&nbsp;BLAH&nbsp;BLAH&gt;</span>" + END, result);
	}
	@Test
	public void testComments() {
		String text = "\n<!--some comment-->";
		String result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"texcomment\">&lt;!--some&nbsp;comment--&gt;</span>" + END, result);
		
		text = "\n<!--some multiline comment\n" +
				"some more comments  -->";
		result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"texcomment\">&lt;!--some&nbsp;multiline&nbsp;comment<br /></span>\n" +
				"<span class=\"texcomment\">some&nbsp;more&nbsp;comments&nbsp;&nbsp;--&gt;</span>" + END, result);
	}
	@Test
	public void testSimpleTagWithoutAttribs() {
		String text = "\n<tag/>";
		String result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"textag\">&lt;tag/&gt;</span>" + END, result);
		
		text = "\n<tag />";
		result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"textag\">&lt;tag</span>&nbsp;<span class=\"textag\">/&gt;</span>" + END, result);
	}
	
	@Test
	public void testSimpleTagWithAttribs() {
		String text = "\n<tag attrib=\"value\"/>";
		String result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"textag\">&lt;tag</span>&nbsp;<span class=\"texattrib\">attrib=</span><span class=\"texvalue\">\"value\"</span><span class=\"textag\">/&gt;</span>" + END, result);
		
		text = "\n<tag question=\"?\"\n" +
				"answer=\"42\" />";
		result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"textag\">&lt;tag</span>&nbsp;<span class=\"texattrib\">question=</span><span class=\"texvalue\">\"?\"</span><br />\n" +
				"<span class=\"texattrib\">answer=</span><span class=\"texvalue\">\"42\"</span>&nbsp;<span class=\"textag\">/&gt;</span>" + END, result);
	}
	
	@Test
	public void testTagWithBody() {
		String text = "\n<tag>body</tag>";
		String result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"textag\">&lt;tag&gt;</span><span class=\"texnormal\">body</span><span class=\"textag\">&lt;/tag&gt;</span>" + END, result);
		
		text = "\n<tag>\n" +
				"    multiline\n" +
				"    body\n" +
				"</tag>";
		result = new XmlTag(new SimpleIndentator()).parse(text, "");
		Assert.assertEquals(BEGIN + "<span class=\"textag\">&lt;tag&gt;</span><span class=\"texnormal\"><br /></span>\n"+
				"<span class=\"texnormal\">&nbsp;&nbsp;&nbsp;&nbsp;multiline<br /></span>\n"+
				"<span class=\"texnormal\">&nbsp;&nbsp;&nbsp;&nbsp;body<br /></span>\n" + 
				"<span class=\"texnormal\"></span><span class=\"textag\">&lt;/tag&gt;</span>" + END, result);
	}
	
}
