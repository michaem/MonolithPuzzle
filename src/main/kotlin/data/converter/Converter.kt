package data.converter

import form.Form
import form.GlyphForm
import form.HighlightedForm
import form.entity.Size
import glyph.DigitGlyph

interface Converter<From, To> {

	operator fun invoke(from: From): To
}

class ListToHighlightedFormConverter
	: Converter<List<String>, Form<Char>> {

	override fun invoke(from: List<String>): Form<Char> {
		val sublist = from.subList(from.indexOf("#Highlighted form") + 1, from.size)

		val size = Size(sublist.size, sublist.size)
		val highlightedForm = HighlightedForm(size)

		for (x in 0 until size.n) {
			val chars = sublist[x].toCharArray().filter { it != ' ' }
			for (y in 0 until size.m) {
				highlightedForm[x, y] = chars[y]
			}
		}

		return highlightedForm
	}
}

class ListToDigitFormConverter(private val highlightedFormConverter: Converter<List<String>, Form<Char>>)
	: Converter<List<String>, Form<DigitGlyph>> {

	override fun invoke(from: List<String>): Form<DigitGlyph> {
		val highlightedForm = highlightedFormConverter(from)

		val sublist = from.subList(1, from.indexOf("#Highlighted form") - 1)

		val size = Size(sublist.size, sublist.size)
		val glyphForm = GlyphForm<DigitGlyph>(size, highlightedForm)

		for (x in 0 until size.n) {
			val digitList = sublist[x].split(" ")
			for (y in 0 until size.m) {
				val digit = digitList[y].toInt()
				glyphForm[x, y] = DigitGlyph(digit, digit != 0)
			}
		}

		return glyphForm
	}

}

class DigitFormToStringConverter
	: Converter<Form<DigitGlyph>, String> {

	override fun invoke(from: Form<DigitGlyph>): String = "#Result\n${from}"
}