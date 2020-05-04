package form

import form.entity.Position
import form.entity.Size
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HighlightedFormTest {

	private val size = Size(5, 5)

	@Test
	fun `init() EXPECT matrix size 5x5`() {
		val form = HighlightedForm(size)

		assert(form[size.n - 1, size.m - 1] == '*')
		assertThrows<ArrayIndexOutOfBoundsException> { (form[size.n, size.m]) }
	}

	@Test
	fun `init() EXPECT matrix with placeholders elements`() {
		val form = HighlightedForm(size)

		for (x in 0 until form.size.n) {
			for (y in 0 until form.size.m) {
				assert(form[x, y] == '*')
			}
		}
	}

	@Test
	fun `get(0, 0) EXPECT placeholder element`() {
		val form = HighlightedForm(size)

		assert(form[0, 0] == '*')
	}

	@Test
	fun `get(Position(0, 0)) EXPECT placeholder element`() {
		val form = HighlightedForm(size)

		assert(form[Position(0, 0)] == '*')
	}

	@Test
	fun `set(0,0) EXPECT 'x' element on position 0,0`() {
		val form = HighlightedForm(size)

		form[0, 0] = 'x'

		assert(form[0, 0] == 'x')
	}

	@Test
	fun `set(Position(0,0, 'x') EXPECT 'x' element on position 0,0`() {
		val form = HighlightedForm(size)

		form[Position(0, 0)] = 'x'

		assert(form[Position(0, 0)] == 'x')
	}
}