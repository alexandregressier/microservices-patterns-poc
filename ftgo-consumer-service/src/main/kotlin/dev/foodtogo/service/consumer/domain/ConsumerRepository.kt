package dev.foodtogo.service.consumer.domain

import com.neovisionaries.i18n.CountryCode
import dev.foodtogo.commons.EmailAddress
import dev.foodtogo.commons.PersonalName
import dev.foodtogo.commons.PhoneNumber
import dev.foodtogo.commons.Place
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository

private val log = KotlinLogging.logger {}

interface ConsumerRepository : JpaRepository<Consumer, ConsumerId>

@Configuration
class ConsumerRepositoryConfig {

    @Profile("development")
    @Bean
    fun preloadConsumerRepository(consumerRepository: ConsumerRepository) = CommandLineRunner {
        if (consumerRepository.count() == 0L)
            log.debug { "Preloading ${consumerRepository.save(
                Consumer(
                    name = PersonalName("Alexandre", "Gressier"),
                    phoneNumber = PhoneNumber("+33677889900"),
                    emailAddress = EmailAddress("alexandre.gressier@example.com"),
                    home = Place(
                        street = "1 Infinite Loop",
                        city = "Cupertino",
                        postalCode = "95014",
                        country = CountryCode.US,
                    ),
                ))}"
            }
    }
}