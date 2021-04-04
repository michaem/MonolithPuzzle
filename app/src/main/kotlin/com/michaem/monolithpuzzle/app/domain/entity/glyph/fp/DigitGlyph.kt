package com.michaem.monolithpuzzle.app.domain.entity.glyph.fp

/**
 * Sample in functional programming style.
 *
 * DigitalGlyph - immutable type
 */
data class DigitGlyph(
	val value: Int = 0,
	val broken: Boolean = false,
	val maxValue: Int
)

/**
 * Pure functions for invoking inner behavior for immutable DigitalGlyph type
 */
private val inc: (DigitGlyph) -> DigitGlyph = { it.copy(value = it.value + 1) }
private val dec: (DigitGlyph) -> DigitGlyph = { it.copy(value = it.value - 1) }
private infix fun Int.setValueTo(glyph: DigitGlyph): DigitGlyph = glyph.copy(value = this)

/**
 * Top-level pure functions for implementing abstraction logic
 */
val next: Next<DigitGlyph> = {
	if (it.value < it.maxValue)
		inc(it)
	else
		0 setValueTo it
}

val previous: Previous<DigitGlyph> = {
	if (it.value > 0)
		dec(it)
	else
		it.maxValue setValueTo it
}

val reset: Reset<DigitGlyph> = { 0 setValueTo it }


