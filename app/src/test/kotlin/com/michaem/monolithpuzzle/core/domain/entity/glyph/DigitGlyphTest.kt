package com.michaem.monolithpuzzle.core.domain.entity.glyph

import com.michaem.monolithpuzzle.app.domain.entity.glyph.DigitGlyph
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DigitGlyphTest {

	private val maxValue = 5

	@Test
	fun `get() EXPECT same value`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertEquals(1, glyph.value)
	}

	@Test
	fun `next() EXPECT next value`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertEquals(2, glyph.next())
	}

	@Test
	fun `next() from max value EXPECT 0`() {
		val glyph = DigitGlyph(maxValue, false, maxValue)
		assertEquals(0, glyph.next())
	}

	@Test
	fun `previous() EXPECT previous value`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertEquals(0, glyph.previous())
	}

	@Test
	fun `previous() from zero EXPECT max value`() {
		val glyph = DigitGlyph(0, false, maxValue)
		assertEquals(maxValue, glyph.previous())
	}

	@Test
	fun `isBroken() EXPECT true`() {
		val glyph = DigitGlyph(1, true, maxValue)
		assertTrue(glyph.isBroken())
	}

	@Test
	fun `isBroken() EXPECT false`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertFalse(glyph.isBroken())
	}

	@Test
	fun `reset() EXPECT 0`() {
		val glyph = DigitGlyph(1, false, maxValue)

		glyph.reset()

		assertEquals(0, glyph.value)
	}
}