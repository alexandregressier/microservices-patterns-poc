package dev.foodtogo.service.order.domain

import com.neovisionaries.i18n.CountryCode.US
import dev.foodtogo.commons.DateTime
import dev.foodtogo.commons.Place
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

private val log = KotlinLogging.logger {}

interface OrderRepository : JpaRepository<Order, OrderId>

@Configuration
class OrderRepositoryConfig {

    @Profile("development")
    @Bean
    fun preloadOrderRepository(orderRepository: OrderRepository) = CommandLineRunner {
        if (orderRepository.count() == 0L)
            log.debug { "Preloading ${orderRepository.save(
                Order(
                    consumerId = UUID.randomUUID(),
                    restaurantId = UUID.randomUUID(),
                    items = mapOf(
                        UUID.randomUUID() to 1,
                        UUID.randomUUID() to 1,
                        UUID.randomUUID() to 2,
                    ),
                    delivery = Order.Delivery(
                        estimatedAt = DateTime.now(),
                        destination = Place(
                            street = "1 Infinite Loop",
                            city = "Cupertino",
                            postalCode = "95014",
                            country = US,
                        ),
                    ),
                    payment = Order.Payment(token = "4575193143401081"),
                ))}"
            }
    }
}