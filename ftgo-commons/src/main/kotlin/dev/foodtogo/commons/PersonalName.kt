package dev.foodtogo.commons

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PersonalName(
    @JsonProperty("firstName") @Column(name = "firstName", nullable = false) val first: String,
    @JsonProperty("lastName") @Column(name = "lastName", nullable = false) val last: String,
)