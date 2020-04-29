package form

import form.entity.Position
import form.entity.Size

interface Form<Element> {

	val size: Size

	fun get(position: Position): Element

	operator fun get(x: Int, y: Int): Element

	fun search(element: Element): Boolean

	fun searchInRow(row: Int, element: Element): Boolean

	fun searchInColumn(column: Int, element: Element): Boolean
}

interface MutableForm<Element> : Form<Element> {

	fun set(position: Position, element: Element)

	operator fun set(x: Int, y: Int, element: Element)
}