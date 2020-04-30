package form

import form.entity.Position
import form.entity.Size

private const val PLACE_HOLDER = '*'

class HighlightedForm(override val size: Size) : MutableForm<Char> {

	private val matrix: Array<Array<Char>>

	init {
		check(size.n > 0) { "_size.n <= 0" }
		check(size.m > 0) { "_size.m <= 0" }
		check(size.n == size.m) { "size.n != size.m" }

		matrix = Array(size.n) {
			Array(size.m) {
				PLACE_HOLDER
			}
		}
	}

	override fun get(position: Position): Char = get(position.x, position.y)

	override operator fun get(x: Int, y: Int): Char = matrix[x][y]

	override fun search(element: Char): Boolean {
		for (x in 0 until size.n) {
			for (y in 0 until size.m) {
				if (matrix[x][y] == element) {
					return true
				}
			}
		}

		return false
	}

	override fun searchInRow(row: Int, element: Char): Boolean {
		for (y in 0 until size.m) {
			if (matrix[row][y] == element) {
				return true
			}
		}

		return false
	}

	override fun searchInColumn(column: Int, element: Char): Boolean {
		for (x in 0 until size.n) {
			if (matrix[x][column] == element) {
				return true
			}
		}

		return false
	}

	override fun set(position: Position, element: Char) {
		set(position.x, position.y, element)
	}

	override operator fun set(x: Int, y: Int, element: Char) {
		matrix[x][y] = element
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