package dev.foodtogo.service.restaurant.domain

import com.neovisionaries.i18n.CountryCode.FR
import dev.foodtogo.commons.Place
import dev.foodtogo.commons.eur
import dev.foodtogo.service.restaurant.domain.Restaurant.Category
import dev.foodtogo.service.restaurant.domain.Restaurant.Category.FAST_FOOD
import dev.foodtogo.service.restaurant.domain.Restaurant.Category.JAPANESE
import dev.foodtogo.service.restaurant.domain.Restaurant.Menu
import dev.foodtogo.service.restaurant.domain.Restaurant.Menu.*
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository

private val log = KotlinLogging.logger {}

interface RestaurantRepository : JpaRepository<Restaurant, RestaurantId>

@Configuration
class RestaurantRepositoryConfig {

    @Profile("development")
    @Bean
    fun preloadRestaurantRepository(restaurantRepository: RestaurantRepository) = CommandLineRunner {
        if (restaurantRepository.count() == 0L) {
            log.debug { "Preloading ${restaurantRepository.save(
                Restaurant(
                    name = "Crusty Food",
                    location = Place(
                        street = "64 Rue de la République",
                        city = "Lyon",
                        postalCode = "69002",
                        country = FR,
                    ),
                    category = FAST_FOOD,
                    menu = Menu(
                        dishes = setOf(
                            Dish("French Fries", eur(2.10)),
                            Dish("Potato Wedges", eur(2.10)),
                            Dish("Hamburger", eur(2.00)),
                            Dish("Cheeseburger", eur(2.60)),
                            Dish("Double Cheeseburger", eur(3.80)),
                            Dish("Chicken Burger", eur(3.70)),
                            Dish("Fish Burger", eur(3.70)),
                            Dish("Chicken Nuggets", eur(2.70), quantity = 5),
                            Dish("Chicken Nuggets", eur(4.70), quantity = 10),
                            Dish("Beef Wrap", eur(4.80)),
                            Dish("Chicken Wrap", eur(4.80)),
                        ),
                        drinks = setOf(
                            Drink("Cola", cL = 50, eur(2.10)),
                            Drink("Lemonade", cL = 50, eur(2.10)),
                            Drink("Orange Juice", cL = 50, eur(2.10)),
                            Drink("Water", cL = 50, eur(1.50)),
                        ),
                        desserts = setOf(
                            Dessert("Cookie", eur(2.00)),
                            Dessert("Brownie", eur(2.40)),
                            Dessert("Ice Cream", eur(2.50)),
                        ),
                    )
                ))}"
            }
            log.debug { "Preloading ${restaurantRepository.save(
                Restaurant(
                    name = "Mama Mia",
                    location = Place(
                        street = "23 Rue Casimir Périer",
                        city = "Lyon",
                        postalCode = "69002",
                        country = FR,
                    ),
                    category = Category.ITALIAN,
                    menu = Menu(
                        dishes = setOf(
                            Dish("Spaghetti Bolognese", eur(4.90)),
                            Dish("Pasta Carbonara", eur(5.90)),
                            Dish("Lasagna", eur(5.90)),
                            Dish("Margherita Pizza", eur(12.50)),
                            Dish("Cheese Pizza", eur(12.50)),
                            Dish("Mushrooms Pizza", eur(12.50)),
                            Dish("Pepperoni Pizza", eur(12.50)),
                            Dish("Chicken Pizza", eur(13.50)),
                            Dish("Vegetarian Pizza", eur(12.50)),
                            Dish("Hawaiian Pizza", eur(13.50)),
                        ),
                        drinks = setOf(
                            Drink("Lemonade", cL = 50, eur(2.10)),
                            Drink("Sangria", cL = 30, eur(9.20)),
                            Drink("Carbonated Water", cL = 50, eur(1.60)),
                            Drink("Water", cL = 50, eur(1.50)),
                        ),
                        desserts = setOf(
                            Dessert("Profiteroles", eur(4.90)),
                            Dessert("Tiramisu", eur(4.90)),
                            Dessert("Panna cotta", eur(4.10)),
                            Dessert("Ice Cream", eur(4.10)),
                        ),
                    )
                ))}"
            }
            log.debug { "Preloading ${restaurantRepository.save(
                Restaurant(
                    name = "Moshi Moshi",
                    location = Place(
                        street = "150 Avenue Félix Faure",
                        city = "Lyon",
                        postalCode = "69003",
                        country = FR,
                    ),
                    category = JAPANESE,
                    menu = Menu(
                        dishes = setOf(
                            Dish("Sushi", eur(8.10), quantity = 5),
                            Dish("Sashimi", eur(6.10), quantity = 5),
                            Dish("Ramen", eur(9.50)),
                            Dish("Soba", eur(9.50)),
                            Dish("Udon", eur(8.50)),
                            Dish("Tsukune", eur(6.10), quantity = 5),
                            Dish("Miso soup", eur(3.80)),
                        ),
                        drinks = setOf(
                            Drink("Sake", cL = 18, eur(2.10)),
                            Drink("Beer", cL = 33, eur(2.50)),
                            Drink("Cola", cL = 50, eur(2.00)),
                            Drink("Lemonade", cL = 50, eur(1.80)),
                            Drink("Orange Juice", cL = 50, eur(1.80)),
                            Drink("Water", cL = 50, eur(1.50)),
                        ),
                        desserts = setOf(
                            Dessert("Purin", eur(2.90)),
                            Dessert("Cookie", eur(1.90)),
                            Dessert("Mochi", eur(3.10), quantity = 5),
                            Dessert("Dorayaki", eur(2.10), quantity = 2),
                            Dessert("Taiyaki", eur(2.10), quantity = 2),
                            Dessert("Ice Cream", eur(3.50)),
                        ),
                    )
                ))}"
            }
        }
    }
}
