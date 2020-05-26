package com.michaem.monolithpuzzle.core.domain.entity.glyph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GlyphTest {

	private val maxValue = 5

	@Test
	fun `get() EXPECT same value`() {
		val glyph = TestGlyph(1, false, maxValue)
		assertEquals(1, glyph.value)
	}

	@Test
	fun `next() EXPECT next value`() {
		val glyph = TestGlyph(1, false, maxValue)
		assertEquals(2, glyph.next())
	}

	@Test
	fun `next() from max value EXPECT 0`() {
		val glyph = TestGlyph(maxValue, false, maxValue)
		assertEquals(0, glyph.next())
	}

	@Test
	fun `previous() EXPECT previous value`() {
		val glyph = TestGlyph(1, false, maxValue)
		assertEquals(0, glyph.previous())
	}

	@Test
	fun `previous() from zero EXPECT max value`() {
		val glyph = TestGlyph(0, false, maxValue)
		assertEquals(maxValue, glyph.previous())
	}

	@Test
	fun `isBroken() EXPECT true`() {
		val glyph = TestGlyph(1, true, maxValue)
		assertTrue(glyph.isBroken())
	}

	@Test
	fun `isBroken() EXPECT false`() {
		val glyph = TestGlyph(1, false, maxValue)
		assertFalse(glyph.isBroken())
	}

	@Test
	fun `reset() EXPECT 0`() {
		val glyph = TestGlyph(1, false, maxValue)

		glyph.reset()

		assertEquals(0, glyph.value)
	}
}