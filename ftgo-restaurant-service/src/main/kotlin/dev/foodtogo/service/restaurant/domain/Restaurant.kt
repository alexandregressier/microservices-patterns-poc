package dev.foodtogo.service.restaurant.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import dev.foodtogo.commons.DateTime
import dev.foodtogo.commons.Place
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.EnumType.STRING

typealias RestaurantId = UUID
typealias MenuItemId = UUID

@Entity
data class Restaurant(
    @Id @GeneratedValue val id: RestaurantId? = null,
    @JsonIgnore @Column(nullable = false) @CreationTimestamp val createdOn: DateTime? = null,
    @JsonIgnore @Column(nullable = false) @UpdateTimestamp val updatedOn: DateTime? = null,

    @Column(nullable = false) val name: String,
    @OneToOne(cascade = [ALL]) val location: Place,
    @Column(nullable = false) @Enumerated(STRING) val category: Category,
    @Embedded val menu: Menu,
) {
    enum class Category { FAST_FOOD, ITALIAN, JAPANESE }

    @Embeddable
    data class Menu(
        @OneToMany(cascade = [ALL]) @JoinColumn(name = "restaurantId") val dishes: Set<Dish>,
        @OneToMany(cascade = [ALL]) @JoinColumn(name = "restaurantId") val drinks: Set<Drink>,
        @OneToMany(cascade = [ALL]) @JoinColumn(name = "restaurantId") val desserts: Set<Dessert>,
    ) {
        @Entity
        @Table(name = "menuItem")
        @DiscriminatorColumn(name = "type")
        abstract class Item {
            @JsonIgnore @Id @GeneratedValue open var id: MenuItemId? = null
            @JsonIgnore @Column(nullable = false) @CreationTimestamp open var createdOn: DateTime? = null
            @JsonIgnore @Column(nullable = false) @UpdateTimestamp open var updatedOn: DateTime? = null

            abstract val name: String
            abstract val price: Double
        }

        @Entity
        @DiscriminatorValue("dish")
        @Where(clause = "type = 'dish'")
        data class Dish(
            @Column(nullable = false) override val name: String,
            @Column(nullable = false) override val price: Double,
            val quantity: Int? = null,
        ) : Item()

        @Entity
        @DiscriminatorValue("drink")
        @Where(clause = "type = 'drink'")
        data class Drink(
            @Column(nullable = false) override val name: String,
            @Column(name = "quantity") val cL: Int,
            @Column(nullable = false) override val price: Double,
        ) : Item()

        @Entity
        @DiscriminatorValue("dessert")
        @Where(clause = "type = 'dessert'")
        data class Dessert(
            @Column(nullable = false) override val name: String,
            @Column(nullable = false) override val price: Double,
            val quantity: Int? = null,
        ) : Item()
    }
}