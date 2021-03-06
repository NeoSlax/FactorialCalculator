package ru.neoslax.factorialcalc

sealed class State {
    object Error : State()
    object Progress : State()
    class Result(val result: String) : State()
}