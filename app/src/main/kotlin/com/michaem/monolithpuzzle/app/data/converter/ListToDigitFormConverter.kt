package com.michaem.monolithpuzzle.app.data.converter

import com.michaem.monolithpuzzle.app.domain.entity.glyph.DigitGlyph
import com.michaem.monolithpuzzle.core.data.converter.Converter
import com.michaem.monolithpuzzle.core.domain.entity.form.GlyphForm
import com.michaem.monolithpuzzle.core.domain.entity.form.HighlightedForm
import com.michaem.monolithpuzzle.core.domain.entity.form.Size

class ListToDigitFormConverter(private val highlightedFormConverter: Converter<List<String>, HighlightedForm<Char>>)
	: Converter<List<String>, GlyphForm<DigitGlyph, Char>> {

	override fun invoke(from: List<String>): GlyphForm<DigitGlyph, Char> {
		val highlightedForm = highlightedFormConverter(from)

		val sublist = from.subList(1, from.indexOf("#Highlighted form") - 1)

		val size = Size(sublist.size, sublist.size)
		val glyphForm = GlyphForm<DigitGlyph, Char>(
			size,
			highlightedForm)

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