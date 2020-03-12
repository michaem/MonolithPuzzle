package glyph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DigitGlyphTest {

	@Test
	fun `get() EXPECT same value`() {
		val glyph = DigitGlyph(value = 1, broken = false)
		assertEquals(1, glyph.get())
	}

	@Test
	fun `next() EXPECT next value`() {
		val glyph = DigitGlyph(value = 1, broken = false)
		assertEquals(2, glyph.next())
	}

	@Test
	fun `next() from max value EXPECT 0`() {
		val glyph = DigitGlyph(value = DigitGlyph.MAX_VALUE, broken = false)
		assertEquals(0, glyph.next())
	}

	@Test
	fun `previous() EXPECT previous value`() {
		val glyph = DigitGlyph(value = 1, broken = false)
		assertEquals(0, glyph.previous())
	}

	@Test
	fun `previous() from zero EXPECT max value`() {
		val glyph = DigitGlyph(value = 0, broken = false)
		assertEquals(DigitGlyph.MAX_VALUE, glyph.previous())
	}

	@Test
	fun `isBroken() EXPECT true`() {
		val glyph = DigitGlyph(value = 1, broken = true)
		assertTrue(glyph.isBroken())
	}

	@Test
	fun `isBroken() EXPECT false`() {
		val glyph = DigitGlyph(value = 1, broken = false)
		assertFalse(glyph.isBroken())
	}

	@Test
	fun `reset() EXPECT 0`() {
		val glyph = DigitGlyph(value = 1, broken = false)

		glyph.reset()

		assertEquals(0, glyph.get())
	}
}