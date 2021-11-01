package com.home.practice.calculatorApp.useCase

import com.home.practice.calculatorApp.dto.ArithmeticOperand
import com.home.practice.calculatorApp.helper.log
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
        log.info("received to calculate: '$expression'")
        val data = parse(expression)
                ?: return "Invalid Expression"

        val calculationService: CalculationService? = calculationServices
                .find { it.supports(data.operand) }

        if (null == calculationService) {
            log.error("Unsupported operation for operand ${data.operand}")
            return "Unsupported expression"
        }

        val result = calculationService.calculate(data.num1, data.num2).toString()

        log.info("expression '$expression' is calculated with result '$result'")

        return result
    }

    fun parse(expression: String): OperationData? {

        val operations = ArithmeticOperand
                .values()
                .filter { expression.contains(it.key) }

        if (operations.size != 1) {
            log.error("${operations.size} operands found. need exactly 1 operand (+ - * /)")
            return null
        }
        val operand = operations.first()

        log.info("'$operand' (${operand.key}) operand has been found")

        val splitted = expression.split(operand.key)
        if (splitted.size != 2) {
            log.error("exactly 2 numbers are needed to solve the expression")
            return null
        }
        val num1: BigInteger = splitted[0].trim().toBigInteger()
        val num2: BigInteger = splitted[1].trim().toBigInteger()

        log.info("Numbers to operate {num1: '$num1', num2: '$num2'}")

        return OperationData(
                num1 = num1,
                operand = operand,
                num2 = num2,
        )
    }
}

data class OperationData(
        val num1: BigInteger,
        val num2: BigInteger,
        val operand: ArithmeticOperand,
)
