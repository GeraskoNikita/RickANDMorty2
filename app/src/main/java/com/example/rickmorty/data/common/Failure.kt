package com.example.rickmorty.data.common

sealed class Failure(open val message: String? = null) {
    data class Network(override val message: String? = "Нет сети") : Failure(message)
    data class Http(override val message: String? = "Ошибка сервера") : Failure(message)
    data class Unknown(override val message: String? = "Неизвестная ошибка") : Failure(message)
}
