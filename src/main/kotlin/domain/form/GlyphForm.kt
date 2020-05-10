package domain.form

import domain.form.entity.Position
import domain.form.entity.Size

class GlyphForm<Glyph : Any>(override val size: Size,
							 private val highlightedForm: Form<Char>) : MutableForm<Glyph>, SearchableForm<Glyph> {

	private val matrix: Array<Array<Any>>

	init {
		check(size.n > 0) { "_size.n <= 0" }
		check(size.m > 0) { "_size.m <= 0" }
		check(size.n == size.m) { "size.n != size.m" }

		matrix = Array(size.n) { Array(size.m) { Any() } }
	}

	override fun get(position: Position): Glyph = get(position.x, position.y)

	@Suppress("UNCHECKED_CAST")
	override operator fun get(x: Int, y: Int): Glyph = matrix[x][y] as Glyph

	override fun search(element: Glyph, offsetPosition: Position): Boolean {
		check(offsetPosition.x in 0 until size.n && offsetPosition.y in 0 until size.m) {
			"offsetPosition $offsetPosition out of range $size"
		}

		return when {
			searchInRow(offsetPosition, element)             -> true
			searchInColumn(offsetPosition, element)          -> true
			searchInPlainDiagonal(offsetPosition, element)   -> true
			searchInReverseDiagonal(offsetPosition, element) -> true
			else                                             -> false
		}
	}

	private fun searchInRow(elementPosition: Position, element: Glyph): Boolean {
		for (y in 0 until size.m) {
			if (y == elementPosition.y) continue
			if (matrix[elementPosition.x][y] == element) return true
		}

		return false
	}

	private fun searchInColumn(elementPosition: Position, element: Glyph): Boolean {
		for (x in 0 until size.n) {
			if (x == elementPosition.x) continue
			if (matrix[x][elementPosition.y] == element) return true
		}

		return false
	}

	private fun searchInPlainDiagonal(elementPosition: Position, element: Glyph): Boolean {
		// Go to the top-left direction from element position
		var x = elementPosition.x - 1
		var y = elementPosition.y - 1
		while (x >= 0 && y >= 0) {
			if (highlightedForm[x, y] != highlightedForm[elementPosition.x, elementPosition.y]) break
			if (matrix[x][y] == element) return true
			x--
			y--
		}

		// Go to the bottom-right direction from element position
		x = elementPosition.x + 1
		y = elementPosition.y + 1
		while (x < size.n && y < size.m) {
			if (highlightedForm[x, y] != highlightedForm[elementPosition.x, elementPosition.y]) break
			if (matrix[x][y] == element) return true
			x++
			y++
		}

		return false
	}

	private fun searchInReverseDiagonal(elementPosition: Position, element: Glyph): Boolean {
		// Go to the top-right direction from element position
		var x = elementPosition.x - 1
		var y = elementPosition.y + 1
		while (x >= 0 && y < size.m) {
			if (highlightedForm[x, y] != highlightedForm[elementPosition.x, elementPosition.y]) break
			if (matrix[x][y] == element) return true
			x--
			y++
		}

		// Go to the bottom-left direction from element position
		x = elementPosition.x + 1
		y = elementPosition.y - 1
		while (x < size.n && y >= 0) {
			if (highlightedForm[x, y] != highlightedForm[elementPosition.x, elementPosition.y]) break
			if (matrix[x][y] == element) return true
			x++
			y--
		}

		return false
	}

	override fun set(position: Position, element: Glyph) {
		set(position.x, position.y, element)
	}

	override operator fun set(x: Int, y: Int, element: Glyph) {
		matrix[x][y] = element
	}

	override fun toString(): String {
		var result = ""

		for (x in 0 until size.n) {
			for (y in 0 until size.m) {
				result += matrix[x][y]
				if (y != size.m - 1) result += " "
			}
			result += "\n"
		}

		return result
	}
}