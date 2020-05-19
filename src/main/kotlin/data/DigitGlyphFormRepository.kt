package data

import data.converter.Converter
import domain.entity.form.GlyphForm
import domain.entity.glyph.DigitGlyph
import domain.repository.GlyphFormRepository
import java.io.File

class DigitGlyphFormRepository(
	private val inputFile: File,
	private val outputFile: File,
	private val convertToGlyphForm: Converter<List<String>, GlyphForm<DigitGlyph, Char>>,
	private val convertToString: Converter<GlyphForm<DigitGlyph, Char>, String>)
	: GlyphFormRepository<DigitGlyph, Char> {

	override fun get(): GlyphForm<DigitGlyph, Char> {
		val list = inputFile.readLines()
		return convertToGlyphForm(list)
	}

	override fun set(glyphForm: GlyphForm<DigitGlyph, Char>) {
		outputFile.apply {
			createNewFile()
			writeText(convertToString(glyphForm))
		}
	}

}