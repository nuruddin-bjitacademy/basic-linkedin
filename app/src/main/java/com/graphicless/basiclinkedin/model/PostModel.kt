package com.graphicless.basiclinkedin.model

data class PostModel(
    val user_name: String,
    val user_image: String,
    val name: String,
    val connection_type: String,
    val title: String,
    val time: String,
    val post_message: String,
    val post_image: String
)
