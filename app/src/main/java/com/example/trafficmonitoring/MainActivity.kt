package com.example.trafficmonitoring

import android.net.TrafficStats
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var lastRxBytes: Long = 0
    private var lastTxBytes: Long = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView = TextView(this).apply {
            textSize = 18f
            setPadding(40, 40, 40, 40)
        }
        setContentView(textView)

        lastRxBytes = TrafficStats.getTotalRxBytes()
        lastTxBytes = TrafficStats.getTotalTxBytes()

        startMonitoring()
    }

    private fun startMonitoring() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val currentRx = TrafficStats.getTotalRxBytes()
                val currentTx = TrafficStats.getTotalTxBytes()

                val rxDiff = currentRx - lastRxBytes
                val txDiff = currentTx - lastTxBytes

                lastRxBytes = currentRx
                lastTxBytes = currentTx

                val text = """
                    📡 Traffic Monitoring
                    --------------------
                    ⬇️ Download: ${rxDiff / 1024} KB
                    ⬆️ Upload:   ${txDiff / 1024} KB
                """.trimIndent()

                textView.text = text

                handler.postDelayed(this, 1000)
            }
        }, 1000)
    }
}

