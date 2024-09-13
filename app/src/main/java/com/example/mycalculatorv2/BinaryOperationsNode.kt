package com.example.mycalculatorv2

class BinaryOperationsNode (private val leftNode: NumberNode, private val operatorToken: Token, private val rightNode: NumberNode) {
    fun evaluate(): Double {
        val leftVal = leftNode.evaluate()
        val rightVal = rightNode.evaluate()

        return when (operatorToken.type) {
            TokenType.PLUS -> leftVal + rightVal
            TokenType.MINUS -> leftVal - rightVal
            TokenType.MUL -> leftVal * rightVal
            TokenType.DIV -> {
                if (rightVal == 0.0) throw Exception("Division By Zero")
                leftVal / rightVal
            }
            else -> throw Exception("Invalid Operator.")
        }
    }
}