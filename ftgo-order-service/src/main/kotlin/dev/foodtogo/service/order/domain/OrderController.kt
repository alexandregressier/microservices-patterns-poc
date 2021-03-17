package dev.foodtogo.service.order.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/orders")
class OrderController {

    @Autowired private lateinit var orderRepository: OrderRepository

    @PostMapping
    fun postOrders(@RequestBody order: Order): Order =
        orderRepository.save(order)

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: OrderId): Order =
        orderRepository.findById(id)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }

    @GetMapping
    fun getOrders(): List<Order> =
        orderRepository.findAll()
}