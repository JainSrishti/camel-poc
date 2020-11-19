package com.axis.camelpoc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.axis.camelpoc.*")
class CamelPocApplication

fun main(args: Array<String>) {
	runApplication<CamelPocApplication>(*args)
}
