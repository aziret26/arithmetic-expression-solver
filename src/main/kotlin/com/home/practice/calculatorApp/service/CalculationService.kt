package com.home.practice.calculatorApp.service

import com.home.practice.calculatorApp.dto.ArithmeticOperand
import org.springframework.stereotype.Service

interface CalculationService {
    fun supports(operand: ArithmeticOperand): Boolean
    fun calculate(num1: Int, num2: Int): Int
}

@Service
internal class AddCalculationService : CalculationService {
    override fun supports(operand: ArithmeticOperand) = operand == ArithmeticOperand.ADD
    override fun calculate(num1: Int, num2: Int) = num1 + num2
}

@Service
internal class SubtractCalculationService : CalculationService {
    override fun supports(operand: ArithmeticOperand) = operand == ArithmeticOperand.SUBTRACT
    override fun calculate(num1: Int, num2: Int) = num1 - num2
}

@Service
internal class MultiplyCalculationService : CalculationService {
    override fun supports(operand: ArithmeticOperand) = operand == ArithmeticOperand.MULTIPLY
    override fun calculate(num1: Int, num2: Int) = num1 * num2
}

@Service
internal class DivisionCalculationService : CalculationService {
    override fun supports(operand: ArithmeticOperand) = operand == ArithmeticOperand.DIVIDE
    override fun calculate(num1: Int, num2: Int) = num1 / num2
}
