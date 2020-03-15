package glyph

interface Glyph<Type : Any> {

	fun get(): Type

	fun next(): Type

	fun previous(): Type

	fun isBroken(): Boolean

	fun reset()
}

internal class DigitGlyph(private var value: Int = 0, private val broken: Boolean) : Glyph<Int> {

	companion object {
		const val MAX_VALUE = 5
	}

	override fun get(): Int = value

	override fun next(): Int {
		check(value in 0..MAX_VALUE)

		if (value < MAX_VALUE)
			value++
		else
			value = 0

		return value
	}

	override fun previous(): Int {
		check(value in 0..MAX_VALUE)

		if (value > 0)
			value--
		else
			value = MAX_VALUE

		return value
	}

	override fun isBroken(): Boolean = broken

	override fun reset() {
		value = 0
	}
}