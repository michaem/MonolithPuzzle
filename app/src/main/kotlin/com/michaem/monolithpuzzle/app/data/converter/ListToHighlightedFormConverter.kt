package com.michaem.monolithpuzzle.app.data.converter

import com.michaem.monolithpuzzle.core.data.converter.Converter
import com.michaem.monolithpuzzle.core.domain.entity.form.HighlightedForm
import com.michaem.monolithpuzzle.core.domain.entity.form.Size

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