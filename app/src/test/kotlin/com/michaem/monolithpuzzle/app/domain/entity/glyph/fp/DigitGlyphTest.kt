package com.michaem.monolithpuzzle.app.domain.entity.glyph.fp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class DigitGlyphTest {

	private val maxValue = 5

	@Test
	fun `get() EXPECT same value`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertEquals(1, glyph.value)
	}

	@Test
	fun `next() EXPECT next value`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertEquals(2, next(glyph).value)
	}

	@Test
	fun `next() from max value EXPECT 0`() {
		val glyph = DigitGlyph(maxValue, false, maxValue)
		assertEquals(0, next(glyph).value)
	}

	@Test
	fun `previous() EXPECT previous value`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertEquals(0, previous(glyph).value)
	}

	@Test
	fun `previous() from zero EXPECT max value`() {
		val glyph = DigitGlyph(0, false, maxValue)
		assertEquals(maxValue, previous(glyph).value)
	}

	@Test
	fun `isBroken() EXPECT true`() {
		val glyph = DigitGlyph(1, true, maxValue)
		assertTrue(glyph.broken)
	}

	@Test
	fun `isBroken() EXPECT false`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertFalse(glyph.broken)
	}

	@Test
	fun `reset() EXPECT 0`() {
		val glyph = DigitGlyph(1, false, maxValue)
		assertEquals(0, reset(glyph).value)
	}
}