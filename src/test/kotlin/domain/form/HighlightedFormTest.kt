package domain.form

import domain.form.entity.Position
import domain.form.entity.Size
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HighlightedFormTest {

	private val size = Size(5, 5)

	@Test
	fun `init() EXPECT matrix size 5x5`() {
		val form = HighlightedForm<Char>(size)

		assertThrows<ArrayIndexOutOfBoundsException> { (form[size.n, size.m]) }
	}

	@Test
	fun `get(0, 0) EXPECT Any element`() {
		val form = HighlightedForm<Char>(size)

		assertEquals(Any::class.java, form[0, 0]::class.java)
	}

	@Test
	fun `get(Position(0, 0)) EXPECT Any element`() {
		val form = HighlightedForm<Char>(size)

		assertEquals(Any::class.java, form[Position(0, 0)]::class.java)
	}

	@Test
	fun `set(0,0) EXPECT 'x' element on position 0,0`() {
		val form = HighlightedForm<Char>(size)

		form[0, 0] = 'x'

		assertEquals('x', form[0, 0])
	}

	@Test
	fun `set(Position(0,0, 'x') EXPECT 'x' element on position 0,0`() {
		val form = HighlightedForm<Char>(size)

		form[Position(0, 0)] = 'x'

		assertEquals('x', form[Position(0, 0)])
	}
}