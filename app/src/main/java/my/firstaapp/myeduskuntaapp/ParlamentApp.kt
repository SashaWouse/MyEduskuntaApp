package my.firstaapp.myeduskuntaapp

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.firstaapp.myeduskuntaapp.work.RefreshDataWorker
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ParlamentApp: Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
            setupRecurringWork()
        }
    }

    /**
     * Setup WorkManager background job to 'fetch' new network data weekly.
     */

    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }
                .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()


        Timber.d("Periodic Work request for sync is scheduled")
        WorkManager.getInstance(appContext).enqueueUniquePeriodicWork(
                RefreshDataWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest)
    }

}