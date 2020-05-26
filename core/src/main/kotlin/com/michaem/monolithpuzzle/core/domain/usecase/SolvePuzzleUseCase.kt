package com.michaem.monolithpuzzle.core.domain.usecase

import com.michaem.monolithpuzzle.core.domain.entity.form.GlyphForm
import com.michaem.monolithpuzzle.core.domain.entity.form.Position
import com.michaem.monolithpuzzle.core.domain.entity.glyph.Glyph
import com.michaem.monolithpuzzle.core.domain.repository.GlyphFormRepository
import com.michaem.monolithpuzzle.core.domain.usecase.Direction.*

class SolvePuzzleUseCase<GlyphType : Glyph<*>, HighlightedType>(
	private val repository: GlyphFormRepository<GlyphType, HighlightedType>) {

	operator fun invoke() {
		val glyphForm: GlyphForm<GlyphType, HighlightedType> = repository.get()

		var x = 0
		var direction = Forward
		while (x in 0 until glyphForm.size.n) {
			var y = 0
			while (y in 0 until glyphForm.size.m) {
				val glyph = glyphForm[x, y]

				if (glyph.isBroken()) {
					when (direction) {
						Forward -> y++
						Back    -> y--
					}
					continue
				}

				var hasSame = true
				while (glyph.next() != 0) {
					hasSame = glyphForm.search(glyph, Position(x, y))
					if (!hasSame) break
				}

				y = when {
					hasSame -> {
						direction = Back
						y - 1 // previous glyph
					}

					else    -> {
						direction = Forward
						y + 1 // next glyph
					}
				}

				if (y < 0) break
			}

			x = when {
				y < 0 -> x - 1 // return on previous row
				else  -> x + 1 // move to next row
			}

			if (y < 0 && x < 0) throw IndexOutOfBoundsException("Glyph form is corrupted, x = $x, y = $y")
		}

		repository.set(glyphForm)
	}
}

private enum class Direction {
	Forward,
	Back,
}