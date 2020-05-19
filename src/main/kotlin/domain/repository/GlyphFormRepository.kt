package domain.repository

import domain.entity.form.GlyphForm
import domain.entity.glyph.Glyph

interface GlyphFormRepository<GlyphType : Glyph<*>, HighlightedType> {

	fun get(): GlyphForm<GlyphType, HighlightedType>

	fun set(glyphForm: GlyphForm<GlyphType, HighlightedType>)
}