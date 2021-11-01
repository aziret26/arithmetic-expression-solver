package com.home.practice.calculatorApp.controller

import com.home.practice.calculatorApp.useCase.CalculatorUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/calculate")
class CalculatorController(
        private val calculatorUseCase: CalculatorUseCase
) {

    @GetMapping
    fun calculate(
            expression: String
    ): String {
        return calculatorUseCase.calculate(expression)
    }

}