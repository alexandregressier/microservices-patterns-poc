package dev.foodtogo.service.consumer.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consumers")
class ConsumerController {

    @Autowired private lateinit var consumerRepository: ConsumerRepository

    @GetMapping
    fun getConsumers(): List<Consumer> = consumerRepository.findAll()
}