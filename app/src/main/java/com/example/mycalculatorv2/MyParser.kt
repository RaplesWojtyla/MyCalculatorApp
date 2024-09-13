package com.example.mycalculatorv2

class MyParser(private val tokens: List<Token>) {
    private var index = -1
    private var currentToken: Token? = null

    init {
        advance()
    }

    private fun advance(): Token? {
        index += 1;
        currentToken = if (index < tokens.size) tokens[index] else null

        return  currentToken
    }

    fun parse(): NumberNode? {
        return expression()
    }

    private fun factor(): NumberNode? {
        val token = currentToken

        if (token?.type == TokenType.INT || token?.type == TokenType.FLOAT) {
            advance()
            return NumberNode(token.value!!)
        }

        return null
    }

    private fun term(): NumberNode? {
        return binaryOperation({ factor() }, setOf(TokenType.MUL, TokenType.DIV))
    }

    private fun expression(): NumberNode? {
        return binaryOperation({ term() }, setOf(TokenType.PLUS, TokenType.MINUS))
    }

    private fun binaryOperation(func: () -> NumberNode?, operations: Set<TokenType>): NumberNode? {
        var left = func()

        while (currentToken != null && currentToken!!.type in operations) {
            val operatorToken = currentToken!!
            advance()
            val right = func()

            if (left != null && right != null) {
                left = NumberNode(BinaryOperationsNode(left, operatorToken, right).evaluate())
            }
        }

        return left
    }
}