package dev.foodtogo.commons

import org.javamoney.moneta.FastMoney
import javax.persistence.AttributeConverter
import javax.persistence.Converter

typealias Money = FastMoney

fun usd(amount: Number): Money = Money.of(amount, "USD")
fun eur(amount: Number): Money = Money.of(amount, "EUR")

@Converter(autoApply = true)
class MoneyConverter : AttributeConverter<Money, String> {
    override fun convertToDatabaseColumn(attribute: Money): String = "$attribute"
    override fun convertToEntityAttribute(dbData: String): Money = Money.parse(dbData)
}