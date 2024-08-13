package com.openaiinvest.app.domain.model

data class UserData(
    val uid: String,
    val email: String,
    val tokens: Int,
    val shares: Int
) {
    constructor() : this("", "", 0, 0)
}
