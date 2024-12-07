package com.app.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExpenseManagerApplication

fun main(args: Array<String>) {
	runApplication<ExpenseManagerApplication>(*args)
}
