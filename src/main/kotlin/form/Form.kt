package form

import form.entity.Position
import form.entity.Size

interface Form<Element> {

	val size: Size

	operator fun get(position: Position): Element

	fun search(element: Element): Boolean

	fun searchInRow(row: Int, element: Element): Boolean

	fun searchInColumn(column: Int, element: Element): Boolean
}

interface MutableForm<Element> : Form<Element> {

	operator fun set(position: Position, element: Element)
}