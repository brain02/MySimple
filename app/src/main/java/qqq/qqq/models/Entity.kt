package qqq.qqq.models

data class PassengersResponse(
    val totalPages: Int,
    val totalPassengers: Int,
    val data: List<Passenger>,
)

data class Passenger(
    val __v: Int,
    val _id: String,
    val airline: List<Airline>,
    val name: String,
    val trips: Int
)

data class Airline(
    val country: String,
    val established: String,
    val head_quaters: String,
    val id: Int,
    val logo: String,
    val name: String,
    val slogan: String,
    val website: String
)