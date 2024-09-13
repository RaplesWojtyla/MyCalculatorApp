package com.example.mycalculatorv2

class Tokenizer(private val input: String) {
    private val tokens = mutableListOf<Token>()
    private var index = 0

    fun tokenize(): List<Token> {
        while (index < input.length) {
            val currChar = input[index]

            when {
                currChar.isDigit() -> {
                    tokens.add(parseNumber())
                }
                currChar == '+' -> {
                    tokens.add(Token(TokenType.PLUS))
                    ++index
                }
                currChar == '-' -> {
                    tokens.add(Token(TokenType.MINUS))
                    ++index
                }
                currChar == '*' -> {
                    tokens.add(Token(TokenType.MUL))
                    ++index
                }
                currChar == '/' -> {
                    tokens.add(Token(TokenType.DIV))
                    ++index
                }
            }
        }

        return tokens
    }

    private fun parseNumber(): Token {
        val start = index

        while(index < input.length && (input[index].isDigit() || input[index] == '.')) {
            ++index
        }

        val strNum = input.substring(start, index)

        return if (strNum.contains('.')) {
            Token(TokenType.FLOAT, strNum.toDouble())
        } else {
            Token(TokenType.INT, strNum.toLong())
        }
    }
}