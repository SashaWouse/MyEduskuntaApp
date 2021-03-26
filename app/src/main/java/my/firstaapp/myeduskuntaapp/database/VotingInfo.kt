package my.firstaapp.myeduskuntaapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voting_info_table")
data class VotingInfo(

        @PrimaryKey(autoGenerate = true)
        var votingId: Int,

        @ColumnInfo(name = "quality_rating")
        var qualityRating: Int = -1,
)