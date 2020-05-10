package data

import data.converter.Converter
import domain.form.Form
import domain.glyph.DigitGlyph
import domain.repository.FormRepository
import java.io.File

class DigitGlyphFormRepository(private val inputFile: File,
							   private val outputFile: File,
							   private val convertToDigitGlyphForm: Converter<List<String>, Form<DigitGlyph>>,
							   private val convertToString: Converter<Form<DigitGlyph>, String>)
	: FormRepository<DigitGlyph> {

	override fun get(): Form<DigitGlyph> {
		val data = inputFile.readLines()
		return convertToDigitGlyphForm(data)
	}

	override fun set(form: Form<DigitGlyph>) {
		outputFile.apply {
			createNewFile()
			writeText(convertToString(form))
		}
	}

}