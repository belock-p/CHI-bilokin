import android.app.Service
import android.content.Intent
import android.os.IBinder

class CounterService : Service() {
    private var counter = 0

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Отримуємо значення counter з Intent
        counter = intent?.getIntExtra("counter", 0) ?: 0
        // Надсилаємо оновлене значення
        sendCounterUpdate()
        return START_STICKY
    }

    private fun sendCounterUpdate() {
        val broadcastIntent = Intent("COUNTER_UPDATE")
        broadcastIntent.putExtra("counter", counter)
        sendBroadcast(broadcastIntent)
    }
}
