package dev.foodtogo.service.restaurant

import dev.foodtogo.commons.Commons
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackageClasses = [App::class, Commons::class])
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}