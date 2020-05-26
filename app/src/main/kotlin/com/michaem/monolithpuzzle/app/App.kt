package com.michaem.monolithpuzzle.app

import com.michaem.monolithpuzzle.app.data.DigitGlyphFormRepository
import com.michaem.monolithpuzzle.app.data.converter.DigitFormToStringConverter
import com.michaem.monolithpuzzle.app.data.converter.ListToDigitFormConverter
import com.michaem.monolithpuzzle.app.data.converter.ListToHighlightedFormConverter
import com.michaem.monolithpuzzle.core.domain.usecase.SolvePuzzleUseCase
import java.io.File
import java.nio.file.Paths

fun main() {
	val toHighlightedFormConverter = ListToHighlightedFormConverter()
	val toDigitFormConverter = ListToDigitFormConverter(toHighlightedFormConverter)
	val toStringConverter = DigitFormToStringConverter()

	val path = Paths.get("", "app", "result").toAbsolutePath().toString()

	val repository = DigitGlyphFormRepository(File("$path/input.txt"),
											  File("$path/output.txt"),
											  toDigitFormConverter,
											  toStringConverter)

	SolvePuzzleUseCase(repository)()
}