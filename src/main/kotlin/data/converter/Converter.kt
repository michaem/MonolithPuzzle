package data.converter

import domain.entity.form.GlyphForm
import domain.entity.form.HighlightedForm
import domain.entity.form.Size
import domain.entity.glyph.DigitGlyph

interface Converter<From, To> {

	operator fun invoke(from: From): To
}

class ListToHighlightedFormConverter
	: Converter<List<String>, HighlightedForm<Char>> {

	override fun invoke(from: List<String>): HighlightedForm<Char> {
		val sublist = from.subList(from.indexOf("#Highlighted form") + 1, from.size)

		val size = Size(sublist.size, sublist.size)
		val highlightedForm = HighlightedForm<Char>(size)

		for (x in 0 until size.n) {
			val chars = sublist[x].toCharArray().filter { it != ' ' }
			for (y in 0 until size.m) {
				highlightedForm[x, y] = chars[y]
			}
		}

		return highlightedForm
	}
}

class ListToDigitFormConverter(private val highlightedFormConverter: Converter<List<String>, HighlightedForm<Char>>)
	: Converter<List<String>, GlyphForm<DigitGlyph, Char>> {

	override fun invoke(from: List<String>): GlyphForm<DigitGlyph, Char> {
		val highlightedForm = highlightedFormConverter(from)

		val sublist = from.subList(1, from.indexOf("#Highlighted form") - 1)

		val size = Size(sublist.size, sublist.size)
		val glyphForm = GlyphForm<DigitGlyph, Char>(size, highlightedForm)

		for (x in 0 until size.n) {
			val digitList = sublist[x].split(" ")
			for (y in 0 until size.m) {
				val digit = digitList[y].toInt()
				// Max digit value based on form elements count
				val digitMax = sublist.size
				glyphForm[x, y] = DigitGlyph(digit, digit != 0, digitMax)
			}
		}

		return glyphForm
	}

}

class DigitFormToStringConverter
	: Converter<GlyphForm<DigitGlyph, Char>, String> {

	override fun invoke(from: GlyphForm<DigitGlyph, Char>): String = "#Result\n${from}"
}