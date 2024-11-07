import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment() {

    private var counter = 0
    private lateinit var textView: TextView
    private lateinit var counterReceiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_counter, container, false)

        textView = view.findViewById(R.id.counterText)
        val button = view.findViewById<Button>(R.id.incrementButton)

        // Отримуємо значення counter з аргументів
        counter = arguments?.getInt("counter") ?: 0
        textView.text = "Counter: $counter"

        // Ініціалізуємо BroadcastReceiver для оновлень
        counterReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                counter = intent?.getIntExtra("counter", 0) ?: 0
                textView.text = "Counter: $counter"
            }
        }
        activity?.registerReceiver(counterReceiver, IntentFilter("COUNTER_UPDATE"))

        // Обробник збільшення counter
        button.setOnClickListener {
            counter++
            val intent = Intent(activity, CounterService::class.java)
            intent.putExtra("counter", counter)
            activity?.startService(intent)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.unregisterReceiver(counterReceiver)
    }

    companion object {
        fun newInstance(counter: Int): CounterFragment {
            val fragment = CounterFragment()
            val args = Bundle()
            args.putInt("counter", counter)
            fragment.arguments = args
            return fragment
        }
    }
}
