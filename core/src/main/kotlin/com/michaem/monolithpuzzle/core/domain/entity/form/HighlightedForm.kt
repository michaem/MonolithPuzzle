package com.michaem.monolithpuzzle.core.domain.entity.form

class HighlightedForm<Type>(override val size: Size) : MutableForm<Type> {

	private val matrix: Array<Array<Any>>

	init {
		check(size.n > 0) { "_size.n <= 0" }
		check(size.m > 0) { "_size.m <= 0" }
		check(size.n == size.m) { "size.n != size.m" }

		matrix = Array(size.n) { Array(size.m) { Any() } }
	}

	override fun get(position: Position): Type = get(position.x, position.y)

	@Suppress("UNCHECKED_CAST")
	override operator fun get(x: Int, y: Int): Type = matrix[x][y] as Type

	override fun set(position: Position, element: Type) {
		set(position.x, position.y, element)
	}

	override operator fun set(x: Int, y: Int, element: Type) {
		matrix[x][y] = element!!
	}

	override fun toString(): String {
		var result = ""

		for (x in 0 until size.n) {
			for (y in 0 until size.m) {
				result += matrix[x][y]
			}
			result += "\n"
		}

		return result
	}
}