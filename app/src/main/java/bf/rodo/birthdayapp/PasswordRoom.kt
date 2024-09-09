package bf.rodo.birthdayapp

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class PasswordRoomItem(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "account") val account: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
)

@Dao
interface PasswordRoomDao {
    @Query("SELECT * FROM PasswordRoomItem")
    fun findAll(): Array<PasswordRoomItem>

    @Insert
    fun insert(item: PasswordRoomItem)

    @Delete
    fun delete(item: PasswordRoomItem)
}

@Database(entities = [PasswordRoomItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun passwordRoomDao(): PasswordRoomDao
}