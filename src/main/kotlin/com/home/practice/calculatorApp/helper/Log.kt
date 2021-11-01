package com.home.practice.calculatorApp.helper

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val Any.log: Logger
    get() = LoggerFactory.getLogger(this.javaClass)
