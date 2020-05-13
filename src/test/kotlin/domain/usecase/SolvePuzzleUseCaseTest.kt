package domain.usecase

import data.converter.ListToDigitFormConverter
import data.converter.ListToHighlightedFormConverter
import domain.form.DigitGlyphForm
import domain.glyph.DigitGlyph
import domain.repository.FormRepository
import io.mockk.Matcher
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolvePuzzleUseCaseTest {

	private lateinit var actualGlyphForm: DigitGlyphForm
	private lateinit var expectGlyphForm: DigitGlyphForm

	private val repository: FormRepository<DigitGlyph> = mockk()

	@BeforeEach
	fun setUp() {
		val actualList = listOf("#Glyph form",
								"0 0 4 0 3",
								"0 5 0 0 0",
								"0 4 3 1 0",
								"0 2 0 3 0",
								"0 0 0 0 5",
								" ",
								"#Highlighted form",
								"x x x y y",
								"x x # y y",
								"z # # # y",
								"z z # u u",
								"z z u u u")

		val expectList = listOf("#Glyph form",
								"2 1 4 5 3",
								"3 5 2 4 1",
								"5 4 3 1 2",
								"1 2 5 3 4",
								"4 3 1 2 5",
								" ",
								"#Highlighted form",
								"x x x y y",
								"x x # y y",
								"z # # # y",
								"z z # u u",
								"z z u u u")

		val toHighlightedForm = ListToHighlightedFormConverter()
		val toDigitForm = ListToDigitFormConverter(toHighlightedForm)
		actualGlyphForm = toDigitForm(actualList)
		expectGlyphForm = toDigitForm(expectList)
	}

	@Test
	fun `solve puzzle using actualGlyphForm EXPECT solved puzzle with expectGlyphForm`() {
		val solvePuzzleUseCase = SolvePuzzleUseCase(repository)

		every { repository.get() } returns actualGlyphForm
		every { repository.set(any()) } returns Unit

		solvePuzzleUseCase()

		verify(exactly = 1) { repository.set(match(DigitGlyphFormMatcher(expectGlyphForm))) }
	}

	private class DigitGlyphFormMatcher(private val digitGlyphForm: DigitGlyphForm) : Matcher<DigitGlyphForm> {

		override fun match(arg: DigitGlyphForm?): Boolean {
			if (arg == null) return false

			for (x in 0 until digitGlyphForm.size.n) {
				for (y in 0 until digitGlyphForm.size.m) {
					if (digitGlyphForm[x, y] != arg[x, y]) return false
				}
			}

			return true
		}

	}
}