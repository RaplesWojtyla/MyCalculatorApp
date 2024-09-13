package com.example.mycalculatorv2

class NumberNode (private val value: Any) {
    fun evaluate(): Double {
        return (value as Number).toDouble()
    }
}