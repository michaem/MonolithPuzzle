package com.michaem.monolithpuzzle.core.data.converter

import com.michaem.monolithpuzzle.app.data.converter.ListToHighlightedFormConverter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListToHighlightedFormConverterTest {

	private val list = listOf("#Highlighted form",
							  "x x x y y",
							  "x x # y y",
							  "z # # # y",
							  "z z # u u",
							  "z z u u u")

	@Test
	fun `convert List to Form EXPECT chars Form`() {
		val toHighlightedForm = ListToHighlightedFormConverter()

		val form = toHighlightedForm(list)

		for (x in 0 until form.size.n) {
			// Start offset after #Highlighted domain.entity.form
			val chars = list[x + 1].toCharArray().filter { it != ' ' }
			for (y in 0 until form.size.m) {
				assertEquals(form[x, y], chars[y])
			}
		}
	}
}