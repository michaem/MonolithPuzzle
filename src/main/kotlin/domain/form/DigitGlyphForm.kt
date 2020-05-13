package domain.form

import domain.form.entity.Position
import domain.form.entity.Size
import domain.glyph.DigitGlyph

class DigitGlyphForm(override val size: Size,
					 private val highlightedForm: HighlightedForm) : MutableForm<DigitGlyph>, SearchableForm<DigitGlyph> {

	private val matrix: Array<Array<DigitGlyph>>

	init {
		check(size.n > 0) { "_size.n <= 0" }
		check(size.m > 0) { "_size.m <= 0" }
		check(size.n == size.m) { "size.n != size.m" }

		matrix = Array(size.n) { Array(size.m) { DigitGlyph() } }
	}

	override fun get(position: Position): DigitGlyph = get(position.x, position.y)

	override operator fun get(x: Int, y: Int): DigitGlyph = matrix[x][y]

	override fun search(element: DigitGlyph, offsetPosition: Position): Boolean {
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

	private fun searchInRow(elementPosition: Position, element: DigitGlyph): Boolean {
		for (y in 0 until size.m) {
			if (y == elementPosition.y) continue
			if (matrix[elementPosition.x][y] == element) return true
		}

		return false
	}

	private fun searchInColumn(elementPosition: Position, element: DigitGlyph): Boolean {
		for (x in 0 until size.n) {
			if (x == elementPosition.x) continue
			if (matrix[x][elementPosition.y] == element) return true
		}

		return false
	}

	private fun searchInPlainDiagonal(elementPosition: Position, element: DigitGlyph): Boolean {
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

	private fun searchInReverseDiagonal(elementPosition: Position, element: DigitGlyph): Boolean {
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

	override fun set(position: Position, element: DigitGlyph) {
		set(position.x, position.y, element)
	}

	override operator fun set(x: Int, y: Int, element: DigitGlyph) {
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