package com.michaem.monolithpuzzle.core.data.converter

import com.michaem.monolithpuzzle.app.data.converter.ListToDigitFormConverter
import com.michaem.monolithpuzzle.app.data.converter.ListToHighlightedFormConverter
import com.michaem.monolithpuzzle.core.domain.entity.form.HighlightedForm
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListToDigitFormConverterTest {

	private val list = listOf("#Glyph form",
							  "0 0 4 0 3",
							  "0 5 0 0 0",
							  "0 4 3 1 0",
							  "0 2 0 3 0",
							  "0 0 0 0 5",
							  " ",
							  "#Highlighted form")

	private val highlightedForm: HighlightedForm<Char> = mockk()
	private val toHighlightedForm: ListToHighlightedFormConverter = mockk()

	@Test
	fun `convert List to DigitGlyphForm EXPECT DigitGlyphForm`() {
		val toDigitForm = ListToDigitFormConverter(toHighlightedForm)

		every { toHighlightedForm.invoke(list) } returns highlightedForm
		val form = toDigitForm(list)

		for (x in 0 until form.size.n) {
			// Start offset after #Glyph domain.entity.form
			val digits = list[x + 1].split(" ")
			for (y in 0 until form.size.m) {
				assertEquals(form[x, y].value, digits[y].toInt())
			}
		}

		verify(exactly = 1) { toHighlightedForm.invoke(list) }
	}
}