package ru.neoslax.factorialcalc

class State(
    val isError: Boolean = false,
    val isInProgress: Boolean = false,
    val factorial: String = ""
)