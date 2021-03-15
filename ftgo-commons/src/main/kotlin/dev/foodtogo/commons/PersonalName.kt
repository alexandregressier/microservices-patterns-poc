package dev.foodtogo.commons

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PersonalName(
    @Column(name = "firstName", nullable = false) val first: String,
    @Column(name = "lastName", nullable = false) val last: String,
)