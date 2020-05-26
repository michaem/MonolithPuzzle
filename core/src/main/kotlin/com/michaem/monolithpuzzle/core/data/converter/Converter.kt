package com.michaem.monolithpuzzle.core.data.converter

interface Converter<From, To> {

	operator fun invoke(from: From): To
}