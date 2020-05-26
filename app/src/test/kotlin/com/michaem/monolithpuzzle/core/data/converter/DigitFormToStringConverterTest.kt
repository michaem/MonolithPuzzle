package com.michaem.monolithpuzzle.core.data.converter

import com.michaem.monolithpuzzle.app.data.converter.DigitFormToStringConverter
import com.michaem.monolithpuzzle.app.domain.entity.glyph.DigitGlyph
import com.michaem.monolithpuzzle.core.domain.entity.form.GlyphForm
import com.michaem.monolithpuzzle.core.domain.entity.form.HighlightedForm
import com.michaem.monolithpuzzle.core.domain.entity.form.Size
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DigitFormToStringConverterTest {

	private val highlightedForm: HighlightedForm<Char> = mockk()
	private val maxValue = 5

	private val actualDigitForm = GlyphForm<DigitGlyph, Char>(Size(5, 5), highlightedForm)
		// I know, it's ridiculous expression, but clean from side effects of algorithms O(n^2) into loops for input data
		.apply {
			set(0, 0, DigitGlyph(0, false, maxValue))
			set(0, 1, DigitGlyph(0, false, maxValue))
			set(0, 2, DigitGlyph(4, true, maxValue))
			set(0, 3, DigitGlyph(0, false, maxValue))
			set(0, 4, DigitGlyph(3, true, maxValue))

			set(1, 0, DigitGlyph(0, false, maxValue))
			set(1, 1, DigitGlyph(5, true, maxValue))
			set(1, 2, DigitGlyph(0, false, maxValue))
			set(1, 3, DigitGlyph(0, false, maxValue))
			set(1, 4, DigitGlyph(0, false, maxValue))

			set(2, 0, DigitGlyph(0, false, maxValue))
			set(2, 1, DigitGlyph(4, true, maxValue))
			set(2, 2, DigitGlyph(3, true, maxValue))
			set(2, 3, DigitGlyph(1, true, maxValue))
			set(2, 4, DigitGlyph(0, false, maxValue))

			set(3, 0, DigitGlyph(0, false, maxValue))
			set(3, 1, DigitGlyph(2, true, maxValue))
			set(3, 2, DigitGlyph(0, false, maxValue))
			set(3, 3, DigitGlyph(3, true, maxValue))
			set(3, 4, DigitGlyph(0, false, maxValue))

			set(4, 0, DigitGlyph(0, false, maxValue))
			set(4, 1, DigitGlyph(0, false, maxValue))
			set(4, 2, DigitGlyph(0, false, maxValue))
			set(4, 3, DigitGlyph(0, false, maxValue))
			set(4, 4, DigitGlyph(5, true, maxValue))
		}

	private val expectString =
		"#Result\n" +
			"0 0 4 0 3\n" +
			"0 5 0 0 0\n" +
			"0 4 3 1 0\n" +
			"0 2 0 3 0\n" +
			"0 0 0 0 5\n"

	@Test
	fun `convert DigitGlyphForm To String EXPECT String`() {
		val toString = DigitFormToStringConverter()
		assertEquals(expectString, toString(actualDigitForm))
	}
}