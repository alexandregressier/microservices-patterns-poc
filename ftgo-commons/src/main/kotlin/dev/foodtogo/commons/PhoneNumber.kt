package dev.foodtogo.commons

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import javax.persistence.AttributeConverter
import javax.persistence.Converter

class PhoneNumber @JsonCreator constructor(phoneNumber: String) : Phonenumber.PhoneNumber() {

    init { mergeFrom(PhoneNumberUtil.getInstance().parse(phoneNumber, "$countryCodeSource")) }

    @JsonValue override fun toString(): String =
        PhoneNumberUtil.getInstance().format(this, PhoneNumberUtil.PhoneNumberFormat.E164)
}

@Converter(autoApply = true)
class PhoneNumberConverter : AttributeConverter<PhoneNumber, String> {
    override fun convertToDatabaseColumn(attribute: PhoneNumber): String = "$attribute"
    override fun convertToEntityAttribute(dbData: String): PhoneNumber = PhoneNumber(dbData)
}