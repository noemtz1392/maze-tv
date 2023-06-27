package mx.com.android.maze.domain.model

data class Show(
    var id: String,
    var name: String,
    var networkName: String?,
    var airDate: String?,
    var airTime: String?,
    var rating: String?,
    var summary: String?,
    var officialSite: String?,
    var genres: String?,
    var imageUrl: String?,
    var schedule: Schedule?
)
