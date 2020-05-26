package com.michaem.monolithpuzzle.core.domain.repository

import com.michaem.monolithpuzzle.core.domain.entity.form.GlyphForm
import com.michaem.monolithpuzzle.core.domain.entity.glyph.Glyph

interface GlyphFormRepository<GlyphType : Glyph<*>, HighlightedType> {

	fun get(): GlyphForm<GlyphType, HighlightedType>

	fun set(glyphForm: GlyphForm<GlyphType, HighlightedType>)
}