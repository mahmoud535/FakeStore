package com.example.fakestore.util.extension


infix fun String.addPrefix(value: String) = value + this

infix fun String.addSuffix(value: String) = this + value