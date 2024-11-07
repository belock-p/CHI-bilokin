import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private lateinit var counterReceiver: CounterReceiver
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.counterText)
        val button = findViewById<Button>(R.id.incrementButton)
        val goToFragmentButton = findViewById<Button>(R.id.goToFragmentButton)

        // Ініціалізуємо BroadcastReceiver для отримання оновлень
        counterReceiver = CounterReceiver(textView)
        registerReceiver(counterReceiver, IntentFilter("COUNTER_UPDATE"))

        // Обробник збільшення counter
        button.setOnClickListener {
            counter++
            startCounterService()
        }

        // Переход до FragmentActivity
        goToFragmentButton.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            intent.putExtra("counter", counter)
            startActivity(intent)
        }
    }

    private fun startCounterService() {
        val intent = Intent(this, CounterService::class.java)
        intent.putExtra("counter", counter)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(counterReceiver)
    }
}
