import data.DigitGlyphFormRepository
import data.converter.DigitFormToStringConverter
import data.converter.ListToDigitFormConverter
import data.converter.ListToHighlightedFormConverter
import domain.usecase.SolvePuzzleUseCase
import java.io.File

fun main() {
	val toHighlightedFormConverter = ListToHighlightedFormConverter()
	val toDigitFormConverter = ListToDigitFormConverter(toHighlightedFormConverter)
	val toStringConverter = DigitFormToStringConverter()
	val repository = DigitGlyphFormRepository(File("data/input.txt"),
											  File("data/output.txt"),
											  toDigitFormConverter,
											  toStringConverter)

	SolvePuzzleUseCase(repository)()
}