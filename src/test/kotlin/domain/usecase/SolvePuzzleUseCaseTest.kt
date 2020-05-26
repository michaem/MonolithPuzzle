package domain.usecase

import data.converter.ListToDigitFormConverter
import data.converter.ListToHighlightedFormConverter
import domain.entity.form.GlyphForm
import domain.entity.glyph.DigitGlyph
import domain.repository.GlyphFormRepository
import io.mockk.Matcher
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SolvePuzzleUseCaseTest {

	private lateinit var actualGlyphForm: GlyphForm<DigitGlyph, Char>
	private lateinit var expectGlyphForm: GlyphForm<DigitGlyph, Char>

	private val repository: GlyphFormRepository<DigitGlyph, Char> = mockk()

	@BeforeEach
	fun setUp() {
		val actualList = listOf("#Glyph form",
								"2 0 0 0",
								"0 1 3 0",
								"0 2 4 0",
								"0 0 0 3",
								" ",
								"#Highlighted form",
								"x x y y",
								"x x y y",
								"z z # #",
								"z z # #")

		val expectList = listOf("#Glyph form",
								"2 3 1 4",
								"4 1 3 2",
								"3 2 4 1",
								"1 4 2 3",
								" ",
								"#Highlighted form",
								"x x y y",
								"x x y y",
								"z z # #",
								"z z # #")

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

	private class DigitGlyphFormMatcher(private val glyphForm: GlyphForm<DigitGlyph, Char>) : Matcher<GlyphForm<DigitGlyph, Char>> {

		override fun match(arg: GlyphForm<DigitGlyph, Char>?): Boolean {
			if (arg == null) return false

			for (x in 0 until glyphForm.size.n) {
				for (y in 0 until glyphForm.size.m) {
					if (glyphForm[x, y] != arg[x, y]) return false
				}
			}

			return true
		}

	}
}