package domain.glyph

interface Glyph<out Type> {

	val value: Type

	fun next(): Type

	fun previous(): Type

	fun isBroken(): Boolean

	fun reset()
}

