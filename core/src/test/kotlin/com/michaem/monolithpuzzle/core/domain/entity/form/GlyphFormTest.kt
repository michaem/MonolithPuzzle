package com.michaem.monolithpuzzle.core.domain.entity.form

import com.michaem.monolithpuzzle.core.domain.entity.glyph.TestGlyph
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GlyphFormTest {

	private val size = Size(5, 5)
	private val maxValue = 5

	@Test
	fun `init() EXPECT matrix size 5x5`() {
		val form = GlyphForm<TestGlyph, Char>(size, mockk())

		assertThrows<ArrayIndexOutOfBoundsException> { (form[size.n, size.m]) }
	}

	@Test
	fun `operator set(0,0) DigitGlyph(0) EXPECT DigitGlyph(0) on position 0,0`() {
		val form = GlyphForm<TestGlyph, Char>(size, mockk())

		form[0, 0] = TestGlyph(0, false, maxValue)

		assert(form[0, 0] == TestGlyph(0, false, maxValue))
	}

	@Test
	fun `operator set(position, DigitGlyph(1)) EXPECT DigitGlyph(1) on position 0,0`() {
		val form = GlyphForm<TestGlyph, Char>(size, mockk())
		val position = Position(0, 0)

		form[position] = TestGlyph(0, false, maxValue)

		assert(form[position] == TestGlyph(0, false, maxValue))
	}

	@Test
	fun `search DigitGlyph(1) in a row EXPECT true`() {
		val form = GlyphForm<TestGlyph, Char>(size, mockk())

		form[3, 3] = TestGlyph(1, false, maxValue)

		assert(form.search(TestGlyph(1, false, maxValue), Position(3, 2)))
	}

	@Test
	fun `search DigitGlyph(1) in a column EXPECT true`() {
		val form = GlyphForm<TestGlyph, Char>(size, mockk())

		form[3, 3] = TestGlyph(1, false, maxValue)

		assert(form.search(TestGlyph(1, false, maxValue), Position(2, 3)))
	}

	@Test
	fun `search DigitGlyph(1) in plane direction EXPECT true`() {
		val highlightedForm: HighlightedForm<Char> = mockk()
		every { highlightedForm[2, 2] } returns '*'
		every { highlightedForm[3, 3] } returns 'u'
		every { highlightedForm[4, 4] } returns 'u'

		val form = GlyphForm<TestGlyph, Char>(size, highlightedForm)
		form[3, 3] = TestGlyph(1, false, maxValue)
		form[4, 4] = TestGlyph(1, false, maxValue)

		assert(form.search(TestGlyph(1, false, maxValue), Position(3, 3)))

		verify(exactly = 0) { highlightedForm[0, 0] }
		verify(exactly = 0) { highlightedForm[1, 1] }
		verify(exactly = 1) { highlightedForm[2, 2] }
		verify(exactly = 2) { highlightedForm[3, 3] }
		verify(exactly = 1) { highlightedForm[4, 4] }
	}

	@Test
	fun `search DigitGlyph(1) in reverse direction EXPECT true`() {
		val highlightedForm: HighlightedForm<Char> = mockk()
		every { highlightedForm[any(), any()] } returns '*'
		every { highlightedForm[2, 4] } returns '*'
		every { highlightedForm[3, 3] } returns 'u'
		every { highlightedForm[4, 2] } returns 'u'

		val form = GlyphForm<TestGlyph, Char>(size, highlightedForm)
		form[3, 3] = TestGlyph(1, false, maxValue)
		form[4, 2] = TestGlyph(1, false, maxValue)

		assert(form.search(TestGlyph(1, false, maxValue), Position(3, 3)))

		verify(exactly = 1) { highlightedForm[2, 4] }
		verify(exactly = 4) { highlightedForm[3, 3] }
		verify(exactly = 1) { highlightedForm[4, 2] }
	}

	@Test
	fun `search DigitGlyph(1) in all directions EXPECT false`() {
		val highlightedForm: HighlightedForm<Char> = mockk()
		every { highlightedForm[2, 2] } returns '*'
		every { highlightedForm[2, 4] } returns '*'
		every { highlightedForm[3, 3] } returns 'u'
		every { highlightedForm[4, 2] } returns 'u'
		every { highlightedForm[4, 4] } returns 'u'

		val form = GlyphForm<TestGlyph, Char>(size, highlightedForm)
		form[3, 3] = TestGlyph(1, false, maxValue)

		assert(!form.search(TestGlyph(1, false, maxValue), Position(3, 3)))

		verify(exactly = 1) { highlightedForm[2, 4] }
		verify(exactly = 4) { highlightedForm[3, 3] }
		verify(exactly = 1) { highlightedForm[4, 2] }
		verify(exactly = 1) { highlightedForm[4, 4] }
	}
}