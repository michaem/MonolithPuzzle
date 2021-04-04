package com.michaem.monolithpuzzle.app.domain.entity.glyph.fp

/**
 * Abstraction functions
 */
typealias Next<Glyph> = (Glyph) -> Glyph

typealias Previous<Glyph> = (Glyph) -> Glyph

typealias Reset<Glyph> = (Glyph) -> Glyph

