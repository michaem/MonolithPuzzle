package domain.repository

import form.Form

interface FormRepository<Element> {

	fun get(): Form<Element>

	fun set(form: Form<Element>)
}