package com.axis.camelpoc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class CamelPocApplication

fun main(args: Array<String>) {
	runApplication<CamelPocApplication>(*args)
}
