package data.converter

import domain.form.DigitGlyphForm
import domain.form.HighlightedForm
import domain.form.entity.Size
import domain.glyph.DigitGlyph

interface Converter<From, To> {

	operator fun invoke(from: From): To
}

class ListToHighlightedFormConverter
	: Converter<List<String>, HighlightedForm> {

	override fun invoke(from: List<String>): HighlightedForm {
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

class ListToDigitFormConverter(private val highlightedFormConverter: Converter<List<String>, HighlightedForm>)
	: Converter<List<String>, DigitGlyphForm> {

	override fun invoke(from: List<String>): DigitGlyphForm {
		val highlightedForm = highlightedFormConverter(from)

		val sublist = from.subList(1, from.indexOf("#Highlighted form") - 1)

		val size = Size(sublist.size, sublist.size)
		val glyphForm = DigitGlyphForm(size, highlightedForm)

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
	: Converter<DigitGlyphForm, String> {

	override fun invoke(from: DigitGlyphForm): String = "#Result\n${from}"
}