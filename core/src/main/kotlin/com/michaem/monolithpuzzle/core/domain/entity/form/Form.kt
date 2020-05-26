package com.michaem.monolithpuzzle.core.domain.entity.form

interface Form<out Element> {

	val size: Size

	operator fun get(position: Position): Element

	operator fun get(x: Int, y: Int): Element
}

interface MutableForm<Element> : Form<Element> {

	operator fun set(position: Position, element: Element)

	operator fun set(x: Int, y: Int, element: Element)
}

interface SearchableForm<Element> : Form<Element> {

	fun search(element: Element, offsetPosition: Position = Position(0, 0)): Boolean
}