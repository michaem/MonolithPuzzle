package com.michaem.monolithpuzzle.core.domain.usecase

import com.michaem.monolithpuzzle.core.domain.entity.form.GlyphForm
import com.michaem.monolithpuzzle.core.domain.entity.form.HighlightedForm
import com.michaem.monolithpuzzle.core.domain.entity.form.Size
import com.michaem.monolithpuzzle.core.domain.entity.glyph.TestGlyph
import com.michaem.monolithpuzzle.core.domain.repository.GlyphFormRepository
import io.mockk.Matcher
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class SolvePuzzleUseCaseTest {

	private val repository: GlyphFormRepository<TestGlyph, Char> = mockk()

	private val maxValue = 4
	private val size = Size(4, 4)

	private val highlightedForm = HighlightedForm<Char>(size)
		.apply {
			set(x = 0, y = 0, element = 'x')
			set(x = 0, y = 1, element = 'x')
			set(x = 0, y = 2, element = 'y')
			set(x = 0, y = 3, element = 'y')

			set(x = 1, y = 0, element = 'x')
			set(x = 1, y = 1, element = 'x')
			set(x = 1, y = 2, element = 'y')
			set(x = 1, y = 3, element = 'y')

			set(x = 2, y = 0, element = 'z')
			set(x = 2, y = 1, element = 'z')
			set(x = 2, y = 2, element = '#')
			set(x = 2, y = 3, element = '#')

			set(x = 3, y = 0, element = 'z')
			set(x = 3, y = 1, element = 'z')
			set(x = 3, y = 2, element = '#')
			set(x = 3, y = 3, element = '#')
		}

	private val actualGlyphForm = GlyphForm<TestGlyph, Char>(size, highlightedForm)
		// I know, it's ridiculous expression, but clean from side effects of algorithms O(n^2) into loops for input data
		.apply {
			set(x = 0, y = 0, element = TestGlyph(_value = 2, broken = true, maxValue = maxValue))
			set(x = 0, y = 1, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))
			set(x = 0, y = 2, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))
			set(x = 0, y = 3, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))

			set(x = 1, y = 0, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))
			set(x = 1, y = 1, element = TestGlyph(_value = 1, broken = true, maxValue = maxValue))
			set(x = 1, y = 2, element = TestGlyph(_value = 3, broken = true, maxValue = maxValue))
			set(x = 1, y = 3, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))

			set(x = 2, y = 0, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))
			set(x = 2, y = 1, element = TestGlyph(_value = 2, broken = true, maxValue = maxValue))
			set(x = 2, y = 2, element = TestGlyph(_value = 4, broken = true, maxValue = maxValue))
			set(x = 2, y = 3, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))

			set(x = 3, y = 0, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))
			set(x = 3, y = 1, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))
			set(x = 3, y = 2, element = TestGlyph(_value = 0, broken = false, maxValue = maxValue))
			set(x = 3, y = 3, element = TestGlyph(_value = 3, broken = true, maxValue = maxValue))
		}

	private val expectGlyphForm = GlyphForm<TestGlyph, Char>(size, highlightedForm)
		// I know, it's ridiculous expression, but clean from side effects of algorithms O(n^2) into loops for input data
		.apply {
			set(x = 0, y = 0, element = TestGlyph(_value = 2, broken = true, maxValue = maxValue))
			set(x = 0, y = 1, element = TestGlyph(_value = 3, broken = false, maxValue = maxValue))
			set(x = 0, y = 2, element = TestGlyph(_value = 1, broken = false, maxValue = maxValue))
			set(x = 0, y = 3, element = TestGlyph(_value = 4, broken = false, maxValue = maxValue))

			set(x = 1, y = 0, element = TestGlyph(_value = 4, broken = false, maxValue = maxValue))
			set(x = 1, y = 1, element = TestGlyph(_value = 1, broken = true, maxValue = maxValue))
			set(x = 1, y = 2, element = TestGlyph(_value = 3, broken = true, maxValue = maxValue))
			set(x = 1, y = 3, element = TestGlyph(_value = 2, broken = false, maxValue = maxValue))

			set(x = 2, y = 0, element = TestGlyph(_value = 3, broken = false, maxValue = maxValue))
			set(x = 2, y = 1, element = TestGlyph(_value = 2, broken = true, maxValue = maxValue))
			set(x = 2, y = 2, element = TestGlyph(_value = 4, broken = true, maxValue = maxValue))
			set(x = 2, y = 3, element = TestGlyph(_value = 1, broken = false, maxValue = maxValue))

			set(x = 3, y = 0, element = TestGlyph(_value = 1, broken = false, maxValue = maxValue))
			set(x = 3, y = 1, element = TestGlyph(_value = 4, broken = false, maxValue = maxValue))
			set(x = 3, y = 2, element = TestGlyph(_value = 2, broken = false, maxValue = maxValue))
			set(x = 3, y = 3, element = TestGlyph(_value = 3, broken = true, maxValue = maxValue))
		}

	@Test
	fun `solve puzzle using actualGlyphForm EXPECT solved puzzle with expectGlyphForm`() {
		val solvePuzzleUseCase = SolvePuzzleUseCase(repository)

		every { repository.get() } returns actualGlyphForm
		every { repository.set(any()) } returns Unit

		solvePuzzleUseCase()

		verify(exactly = 1) { repository.set(match(TestGlyphFormMatcher(expectGlyphForm))) }
	}

	private class TestGlyphFormMatcher(private val glyphForm: GlyphForm<TestGlyph, Char>) : Matcher<GlyphForm<TestGlyph, Char>> {

		override fun match(arg: GlyphForm<TestGlyph, Char>?): Boolean {
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