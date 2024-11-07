import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class CounterReceiver(private val textView: TextView) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val newCounter = intent.getIntExtra("counter", 0)
        textView.text = "Counter: $newCounter"
    }
}
