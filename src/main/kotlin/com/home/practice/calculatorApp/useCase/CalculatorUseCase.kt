package com.home.practice.calculatorApp.useCase

import com.home.practice.calculatorApp.dto.ArithmeticOperand
import com.home.practice.calculatorApp.service.CalculationService
import org.springframework.stereotype.Service
import java.math.BigInteger

interface CalculatorUseCase {
    fun calculate(expression: String): String
}

@Service
internal class CalculatorUseCaseImpl(
        private val calculationServices: Collection<CalculationService>
) : CalculatorUseCase {

    override fun calculate(expression: String): String {
        val data = parse(expression)
                ?: return "Invalid Expression"

        val calculationService: CalculationService = calculationServices
                .find { it.supports(data.operand) }
                ?: return "Unsupported expression"

        return calculationService.calculate(data.num1, data.num2).toString()
    }

    fun parse(expression: String): OperationData? {

        val operations = ArithmeticOperand
                .values()
                .filter { expression.contains(it.key) }

        if (operations.size != 1) {
            return null
        }
        val operation = operations.first()

        val splitted = expression.split(operation.key)
        if (splitted.isEmpty() || splitted.size < 2) {
            return null
        }
        val num1: BigInteger = splitted[0].trim().toBigInteger()
        val num2: BigInteger = splitted[1].trim().toBigInteger()

        return OperationData(
                num1 = num1,
                operand = operation,
                num2 = num2,
        )
    }
}

data class OperationData(
        val num1: BigInteger,
        val num2: BigInteger,
        val operand: ArithmeticOperand,
)
