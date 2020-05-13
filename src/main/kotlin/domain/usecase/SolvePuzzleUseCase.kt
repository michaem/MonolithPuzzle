package domain.usecase

import domain.form.DigitGlyphForm
import domain.form.entity.Position
import domain.glyph.DigitGlyph
import domain.repository.FormRepository

class SolvePuzzleUseCase(private val repository: FormRepository<DigitGlyph>) {

	operator fun invoke() {
		val digitGlyphForm: DigitGlyphForm = repository.get() as DigitGlyphForm

		var x = 0
		while (x in 0 until digitGlyphForm.size.n) {
			var y = 0
			while (y in 0 until digitGlyphForm.size.m) {
				val digitGlyph = digitGlyphForm[x, y]

				if (digitGlyph.isBroken()) {
					y++
					continue
				}

				var hasSame = true
				while (digitGlyph.next() != 0) {
					hasSame = digitGlyphForm.search(digitGlyph, Position(x, y))
					if (!hasSame) break
				}

				y = when {
					hasSame -> {
						when {
							digitGlyphForm[x, y - 1].isBroken() -> y - 2 // previous glyph before broken
							else                                -> y - 1 // previous glyph
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

		repository.set(digitGlyphForm)
	}
}