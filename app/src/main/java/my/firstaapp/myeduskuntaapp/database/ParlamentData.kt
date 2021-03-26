package my.firstaapp.myeduskuntaapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class ParlamentRecords (val records:List<ParlamentData>)

@Parcelize
@Entity(tableName = "parlament_members_table")
data class ParlamentData(
    @PrimaryKey
    val memberId: Int,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "surname")
    val surname: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "party")
    val party: String,
    @ColumnInfo(name = "minister")
    val member: Boolean,
    @ColumnInfo(name = "img_url")
    val pictureUrl: String
): Parcelable