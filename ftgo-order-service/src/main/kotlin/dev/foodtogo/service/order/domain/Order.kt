package dev.foodtogo.service.order.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import dev.foodtogo.commons.DateTime
import dev.foodtogo.commons.Place
import dev.foodtogo.service.order.domain.Order.Status.RECEIVED
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.EnumType.STRING

typealias OrderId = UUID
typealias ConsumerId = UUID
typealias RestaurantId = UUID
typealias MenuItemId = UUID
typealias Quantity = Int
typealias PaymentToken = String

@Entity @Table(name = "`order`")
data class Order (
    @JsonProperty(access = READ_ONLY) @Id @GeneratedValue val id: OrderId? = null,
    @JsonProperty(access = READ_ONLY) @Column(nullable = false) @CreationTimestamp val createdOn: DateTime? = null,
    @JsonProperty(access = READ_ONLY) @Column(nullable = false) @UpdateTimestamp val updatedOn: DateTime? = null,

    @JsonProperty(access = READ_ONLY) @Column(nullable = false) @Enumerated(STRING) val status: Status = RECEIVED,
    @Column(nullable = false) val consumerId: ConsumerId,
    @Column(nullable = false) val restaurantId: RestaurantId,
    @ElementCollection @CollectionTable(name = "order_item")
    @MapKeyColumn(name = "menu_item_id", nullable = false) @Column(name = "quantity", nullable = false)
    val items: Map<MenuItemId, Quantity>,
    @Embedded val delivery: Delivery,
    @Embedded val payment: Payment,
) {
    enum class Status { RECEIVED, ACCEPTED, REFUSED, PREPARING, ON_THE_WAY, DELIVERED }

    @Embeddable
    data class Delivery(
        @Column(nullable = false) val estimatedAt: DateTime,
        @OneToOne(cascade = [ALL]) @JoinColumn(nullable = false) val destination: Place,
        val at: DateTime? = null,
    )

    @Embeddable
    data class Payment(
        @Column(nullable = false) val token: PaymentToken
    )
}