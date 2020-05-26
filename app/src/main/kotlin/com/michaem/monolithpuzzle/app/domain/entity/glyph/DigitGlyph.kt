package com.michaem.monolithpuzzle.app.domain.entity.glyph

import com.michaem.monolithpuzzle.core.domain.entity.glyph.Glyph

class DigitGlyph(private var _value: Int = 0,
				 private val broken: Boolean = false,
				 private val maxValue: Int) : Glyph<Int> {

	override val value: Int
		get() = _value

	override fun next(): Int {
		check(_value in 0..maxValue)

		if (_value < maxValue)
			_value++
		else
			_value = 0

		return _value
	}

	override fun previous(): Int {
		check(_value in 0..maxValue)

		if (_value > 0)
			_value--
		else
			_value = maxValue

		return _value
	}

	override fun isBroken(): Boolean = broken

	override fun reset() {
		_value = 0
	}

	override fun toString(): String = _value.toString()

	override fun equals(other: Any?): Boolean {
		if (javaClass != other?.javaClass) return false

		other as DigitGlyph

		if (_value != other._value) return false

		return true
	}

	override fun hashCode(): Int = _value
}