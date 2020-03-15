package glyph

interface Glyph<Type : Any> {

	fun get(): Type

	fun next(): Type

	fun previous(): Type

	fun isBroken(): Boolean

	fun reset()
}

internal class DigitGlyph(private var _value: Int = 0, private val _broken: Boolean) : Glyph<Int> {

	companion object {
		const val MAX_VALUE = 5
	}

	override fun get(): Int = _value

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
}