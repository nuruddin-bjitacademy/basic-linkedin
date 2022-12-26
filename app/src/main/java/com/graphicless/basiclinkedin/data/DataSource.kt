package com.graphicless.basiclinkedin.data

import com.graphicless.basiclinkedin.R
import com.graphicless.basiclinkedin.model.PostModel

class DataSource {

    fun loadPosts(): List<PostModel> {
        return listOf<PostModel>(
            PostModel(R.string.user_image_1, R.string.user_name_1, R.string.connection_type_1, R.string.title_1, R.string.time_1,R.string.message_1, R.string.post_image_1),
            PostModel(R.string.user_image_2, R.string.user_name_2, R.string.connection_type_2, R.string.title_2, R.string.time_2,R.string.message_2, R.string.post_image_2),
            PostModel(R.string.user_image_3, R.string.user_name_3, R.string.connection_type_3, R.string.title_3, R.string.time_3,R.string.message_3, R.string.post_image_3)
        )
    }
}