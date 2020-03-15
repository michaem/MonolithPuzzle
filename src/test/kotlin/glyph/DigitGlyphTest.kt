package glyph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DigitGlyphTest {

	@Test
	fun `get() EXPECT same value`() {
		val glyph = DigitGlyph(1, false)
		assertEquals(1, glyph.get())
	}

	@Test
	fun `next() EXPECT next value`() {
		val glyph = DigitGlyph(1, false)
		assertEquals(2, glyph.next())
	}

	@Test
	fun `next() from max value EXPECT 0`() {
		val glyph = DigitGlyph(DigitGlyph.MAX_VALUE, false)
		assertEquals(0, glyph.next())
	}

	@Test
	fun `previous() EXPECT previous value`() {
		val glyph = DigitGlyph(1, false)
		assertEquals(0, glyph.previous())
	}

	@Test
	fun `previous() from zero EXPECT max value`() {
		val glyph = DigitGlyph(0, false)
		assertEquals(DigitGlyph.MAX_VALUE, glyph.previous())
	}

	@Test
	fun `isBroken() EXPECT true`() {
		val glyph = DigitGlyph(1, true)
		assertTrue(glyph.isBroken())
	}

	@Test
	fun `isBroken() EXPECT false`() {
		val glyph = DigitGlyph(1, false)
		assertFalse(glyph.isBroken())
	}

	@Test
	fun `reset() EXPECT 0`() {
		val glyph = DigitGlyph(1, false)

		glyph.reset()

		assertEquals(0, glyph.get())
	}
}