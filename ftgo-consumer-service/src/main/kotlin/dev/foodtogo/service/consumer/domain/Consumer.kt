package dev.foodtogo.service.consumer.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonUnwrapped
import dev.foodtogo.commons.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.ALL

typealias ConsumerId = UUID

@Entity
data class Consumer(
    @Id @GeneratedValue val id: ConsumerId? = null,
    @JsonIgnore @Column(nullable = false) @CreationTimestamp val createdOn: DateTime? = null,
    @JsonIgnore @Column(nullable = false) @UpdateTimestamp val updatedOn: DateTime? = null,

    @JsonUnwrapped @Embedded val name: PersonalName,
    @Column(nullable = false, unique = true) val phoneNumber: PhoneNumber,
    @Column(unique = true) val emailAddress: EmailAddress? = null,
    @OneToOne(cascade = [ALL]) val home: Place? = null,
    @OneToOne(cascade = [ALL]) val work: Place? = null,
)