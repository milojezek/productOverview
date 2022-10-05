package eu.milo4apps.notinotask.network

import com.squareup.moshi.Json

data class NestedJsonModel(
    val vpProductByIds: List<Product>
)

data class Product(
    val imageUrl: String,
    val productId: String,
    val brand: BrandData,
    val name: String,
    val annotation: String,
    @Json(name="reviewSummary")
    val rating: ReviewSummary,
    @Json(name="price")
    var price: Price,
)

data class BrandData(
    val id: String,
    val name: String
)

data class ReviewSummary(
    var score: String,
    var averageRating: String
)

data class Price(
    var value: String,
    val currency: String
)

