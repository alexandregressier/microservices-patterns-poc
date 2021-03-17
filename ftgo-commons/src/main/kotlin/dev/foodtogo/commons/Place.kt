package dev.foodtogo.commons

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.persistence.EnumType.STRING

typealias PlaceId = UUID
typealias PostalCode = String

@Entity
data class Place(
    @JsonProperty(access = READ_ONLY) @Id @GeneratedValue val id: PlaceId? = null,
    @JsonIgnore @Column(nullable = false) @CreationTimestamp val createdOn: DateTime? = null,
    @JsonIgnore @Column(nullable = false) @UpdateTimestamp val updatedOn: DateTime? = null,

    @Column(nullable = false) val street: String,
    @Column(nullable = false) val city: String,
    @Column(length = 10, nullable = false) val postalCode: PostalCode,
    @Column(length = 2, nullable = false) @Enumerated(STRING) val country: Country,
)