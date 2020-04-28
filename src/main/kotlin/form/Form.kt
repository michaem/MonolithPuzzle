package form

import form.entity.Position
import form.entity.Size

interface Form<Element> {

	val size: Size

	operator fun get(position: Position): Element

	fun <Element> find(predicate: (value: Element) -> Boolean): Element?

	fun findInRange(element: Element, start: Position, end: Position): Boolean
}

