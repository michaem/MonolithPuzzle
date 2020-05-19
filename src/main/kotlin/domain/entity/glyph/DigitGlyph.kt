package domain.entity.glyph

class DigitGlyph(private var _value: Int = 0, private val _broken: Boolean = false) : Glyph<Int> {

	companion object {
		const val MAX_VALUE = 5
	}

	override val value: Int
		get() = _value

	override fun next(): Int {
		check(_value in 0..MAX_VALUE)

		if (_value < MAX_VALUE)
			_value++
		else
			_value = 0

		return _value
	}

	override fun previous(): Int {
		check(_value in 0..MAX_VALUE)

		if (_value > 0)
			_value--
		else
			_value = MAX_VALUE

		return _value
	}

	override fun isBroken(): Boolean = _broken

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