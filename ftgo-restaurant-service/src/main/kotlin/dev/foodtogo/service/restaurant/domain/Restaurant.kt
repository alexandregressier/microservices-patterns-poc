package dev.foodtogo.service.restaurant.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import dev.foodtogo.commons.DateTime
import dev.foodtogo.commons.Money
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
    @JsonProperty(access = READ_ONLY) @Id @GeneratedValue val id: RestaurantId? = null,
    @JsonIgnore @Column(nullable = false) @CreationTimestamp val createdOn: DateTime? = null,
    @JsonIgnore @Column(nullable = false) @UpdateTimestamp val updatedOn: DateTime? = null,

    @Column(nullable = false) val name: String,
    @OneToOne(cascade = [ALL]) @JoinColumn(nullable = false) val location: Place,
    @Column(nullable = false) @Enumerated(STRING) val category: Category,
    @Embedded val menu: Menu,
) {
    enum class Category { FAST_FOOD, ITALIAN, JAPANESE }

    @Embeddable
    data class Menu(
        @OneToMany(cascade = [ALL]) @JoinColumn(name = "restaurantId", nullable = false) val dishes: Set<Dish>,
        @OneToMany(cascade = [ALL]) @JoinColumn(name = "restaurantId", nullable = false) val drinks: Set<Drink>,
        @OneToMany(cascade = [ALL]) @JoinColumn(name = "restaurantId", nullable = false) val desserts: Set<Dessert>,
    ) {
        @Entity @Table(name = "menuItem") @DiscriminatorColumn(name = "type")
        abstract class Item {
            @JsonProperty(access = READ_ONLY) @Id @GeneratedValue open val id: MenuItemId? = null
            @JsonIgnore @Column(nullable = false) @CreationTimestamp open val createdOn: DateTime? = null
            @JsonIgnore @Column(nullable = false) @UpdateTimestamp open val updatedOn: DateTime? = null

            abstract val name: String
            abstract val price: Money
        }

        @Entity @DiscriminatorValue("dish") @Where(clause = "type = 'dish'")
        data class Dish(
            @Column(nullable = false) override val name: String,
            @Column(nullable = false) override val price: Money,
            val quantity: Int? = null,
        ) : Item()

        @Entity @DiscriminatorValue("drink") @Where(clause = "type = 'drink'")
        data class Drink(
            @Column(nullable = false) override val name: String,
            @get:JsonProperty("cL") @Column(name = "quantity") val cL: Int? = null,
            @Column(nullable = false) override val price: Money,
        ) : Item()

        @Entity @DiscriminatorValue("dessert") @Where(clause = "type = 'dessert'")
        data class Dessert(
            @Column(nullable = false) override val name: String,
            @Column(nullable = false) override val price: Money,
            val quantity: Int? = null,
        ) : Item()
    }
}