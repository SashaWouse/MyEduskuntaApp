package my.firstaapp.myeduskuntaapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bumptech.glide.load.HttpException
import my.firstaapp.myeduskuntaapp.repositories.ParlamentMembersRepository
import timber.log.Timber

class RefreshDataWorker (appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "my.firstaapp.myeduskuntaapp.work.RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val repository = ParlamentMembersRepository

        try {
            repository.refreshParlamentData()
            Timber.d("WorkManager: Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
}