package dev.foodtogo.service.restaurant.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/restaurants")
class RestaurantController {

    @Autowired private lateinit var restaurantRepository: RestaurantRepository

    @PostMapping
    fun postRestaurants(@RequestBody restaurant: Restaurant): Restaurant =
        restaurantRepository.save(restaurant)

    @GetMapping("/{id}")
    fun getRestaurant(@PathVariable id: RestaurantId): Restaurant =
        restaurantRepository.findById(id)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }

    @GetMapping
    fun getRestaurants(): List<Restaurant> =
        restaurantRepository.findAll()
}