package dev.foodtogo.commons

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import javax.persistence.AttributeConverter
import javax.persistence.Converter

data class EmailAddress(
    val localPart: String,
    val domainName: String,
) {
    @JsonCreator constructor(emailAddress: String) :
            this(emailAddress.substringBefore("@"), emailAddress.substringAfter("@"))

    @JsonValue override fun toString() = "$localPart@$domainName"
}

@Converter(autoApply = true)
class EmailAddressConverter : AttributeConverter<EmailAddress, String> {
    override fun convertToDatabaseColumn(attribute: EmailAddress): String = "$attribute"
    override fun convertToEntityAttribute(dbData: String): EmailAddress = EmailAddress(dbData)
}