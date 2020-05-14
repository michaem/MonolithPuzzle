package domain.usecase

import domain.form.GlyphForm
import domain.form.entity.Position
import domain.glyph.Glyph
import domain.repository.GlyphFormRepository

class SolvePuzzleUseCase<GlyphType : Glyph<*>, HighlightedType>(
	private val repository: GlyphFormRepository<GlyphType, HighlightedType>) {

	operator fun invoke() {
		val glyphForm: GlyphForm<GlyphType, HighlightedType> = repository.get()

		var x = 0
		while (x in 0 until glyphForm.size.n) {
			var y = 0
			while (y in 0 until glyphForm.size.m) {
				val digitGlyph = glyphForm[x, y]

				if (digitGlyph.isBroken()) {
					y++
					continue
				}

				var hasSame = true
				while (digitGlyph.next() != 0) {
					hasSame = glyphForm.search(digitGlyph, Position(x, y))
					if (!hasSame) break
				}

				y = when {
					hasSame -> {
						when {
							glyphForm[x, y - 1].isBroken() -> y - 2 // previous glyph before broken
							else                           -> y - 1 // previous glyph
						}
					}

					else    -> {
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