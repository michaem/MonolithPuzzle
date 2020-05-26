package com.michaem.monolithpuzzle.app.data.converter

import com.michaem.monolithpuzzle.app.domain.entity.glyph.DigitGlyph
import com.michaem.monolithpuzzle.core.data.converter.Converter
import com.michaem.monolithpuzzle.core.domain.entity.form.GlyphForm

class DigitFormToStringConverter
	: Converter<GlyphForm<DigitGlyph, Char>, String> {

	override fun invoke(from: GlyphForm<DigitGlyph, Char>): String = "#Result\n${from}"
}