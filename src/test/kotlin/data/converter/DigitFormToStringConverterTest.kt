package data.converter

import domain.form.GlyphForm
import domain.form.HighlightedForm
import domain.form.entity.Size
import domain.glyph.DigitGlyph
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DigitFormToStringConverterTest {

	private val highlightedForm: HighlightedForm = mockk()

	private val actualDigitForm = GlyphForm<DigitGlyph>(Size(5, 5), highlightedForm)
		// I know, it's ridiculous expression, but clear from side effects of algorithms O(n^2) into loops for input data
		.apply {
			set(0, 0, DigitGlyph(0, false))
			set(0, 1, DigitGlyph(0, false))
			set(0, 2, DigitGlyph(4, true))
			set(0, 3, DigitGlyph(0, false))
			set(0, 4, DigitGlyph(3, true))

			set(1, 0, DigitGlyph(0, false))
			set(1, 1, DigitGlyph(5, true))
			set(1, 2, DigitGlyph(0, false))
			set(1, 3, DigitGlyph(0, false))
			set(1, 4, DigitGlyph(0, false))

			set(2, 0, DigitGlyph(0, false))
			set(2, 1, DigitGlyph(4, true))
			set(2, 2, DigitGlyph(3, true))
			set(2, 3, DigitGlyph(1, true))
			set(2, 4, DigitGlyph(0, false))

			set(3, 0, DigitGlyph(0, false))
			set(3, 1, DigitGlyph(2, true))
			set(3, 2, DigitGlyph(0, false))
			set(3, 3, DigitGlyph(3, true))
			set(3, 4, DigitGlyph(0, false))

			set(4, 0, DigitGlyph(0, false))
			set(4, 1, DigitGlyph(0, false))
			set(4, 2, DigitGlyph(0, false))
			set(4, 3, DigitGlyph(0, false))
			set(4, 4, DigitGlyph(5, true))
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