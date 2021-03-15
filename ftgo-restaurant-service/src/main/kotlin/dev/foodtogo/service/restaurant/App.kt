package dev.foodtogo.service.restaurant

import com.fasterxml.jackson.databind.Module
import dev.foodtogo.commons.Commons
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.zalando.jackson.datatype.money.MoneyModule

@SpringBootApplication
@EntityScan(basePackageClasses = [App::class, Commons::class])
class App  {
    @Bean fun moneyModule(): Module = MoneyModule()
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}