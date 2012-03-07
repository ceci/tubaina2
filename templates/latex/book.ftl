<#include "begin.ftl">

<#list book.chapters as chapter>
\npnchapter{${parser.parse(chapter.title)}}
${chapter.getIntroduction()}
	<#list chapter.sections as section>
\section{${parser.parse(section.title!"")}}			
   		<#list section.chunks as chunk>
${chunk.asString()!""}
   		</#list>
	</#list>
</#list>		

<#include "answerTemplate.ftl">

<#include "end.ftl">
