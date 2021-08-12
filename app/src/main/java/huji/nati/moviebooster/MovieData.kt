package huji.nati.moviebooster

import java.io.Serializable

data class MovieData(
    var title :String = "",
    var release_date:String = "",
    var poster_path :String = "",
    var original_language:String = "",
    var vote_average: Float = 0f,
    var vote_count: Int = 0
) : Serializable {}