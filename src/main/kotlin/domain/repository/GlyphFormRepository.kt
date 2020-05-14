package domain.repository

import domain.form.GlyphForm
import domain.glyph.Glyph

interface GlyphFormRepository<GlyphType : Glyph<*>, HighlightedType> {

	fun get(): GlyphForm<GlyphType, HighlightedType>

	fun set(glyphForm: GlyphForm<GlyphType, HighlightedType>)
}