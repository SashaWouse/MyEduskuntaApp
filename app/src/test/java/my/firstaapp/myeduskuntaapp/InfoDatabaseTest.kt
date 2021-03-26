package my.firstaapp.myeduskuntaapp

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import my.firstaapp.myeduskuntaapp.database.VotingDatabase
import my.firstaapp.myeduskuntaapp.database.VotingDatabaseDao
import my.firstaapp.myeduskuntaapp.database.VotingInfo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InfoDatabaseTest {

    private lateinit var votingDao: VotingDatabaseDao
    private lateinit var db: VotingDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, VotingDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        votingDao = db.votingDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertAndGetNight() {
        val info = VotingInfo()
        votingDao.insert(info)
        val thisTime = votingDao.getThisTime()
        assertEquals(thisTime?.qualityRating, -1)
    }
}