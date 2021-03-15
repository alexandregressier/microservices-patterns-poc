package dev.foodtogo.service.consumer.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/consumers")
class ConsumerController {

    @Autowired private lateinit var consumerRepository: ConsumerRepository

    @PostMapping
    fun postConsumers(@RequestBody consumer: Consumer): Consumer =
        consumerRepository.save(consumer)

    @GetMapping("/{id}")
    fun getConsumer(@PathVariable id: ConsumerId): Consumer =
        consumerRepository.findById(id)
            .orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }

    @GetMapping
    fun getConsumers(): List<Consumer> =
        consumerRepository.findAll()
}