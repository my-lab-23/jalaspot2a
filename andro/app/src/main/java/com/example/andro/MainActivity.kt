package com.example.andro

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

import com.example.my_http.MyHTTP

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById(R.id.button1) as Button
        val button2 = findViewById(R.id.button2) as Button
        val button3 = findViewById(R.id.button3) as Button
        val button4 = findViewById(R.id.button4) as Button
        val webView = findViewById(R.id.webView) as WebView
        val editText = findViewById(R.id.editText) as EditText

        fun message(message: String) {
            val encodedHtml = Base64.encodeToString(message.toByteArray(), Base64.NO_PADDING)
            webView.loadData(encodedHtml, "text/html", "base64")
        }

        fun onClick(path_label: String) {
            lifecycleScope.launch {
                val address = "http://192.168.1.196:9000/"
                val response = MyHTTP.get("${address}${path_label}")
                message(response)
            }
        }

        button1.setOnClickListener {
            message("")
            val label = editText.text
            onClick("load?label=${label}")
        }

        button2.setOnClickListener {
            message("")
            val label = editText.text
            onClick("save?label=${label}")
        }

        button3.setOnClickListener {
            message("")
            onClick("remove")
        }

        button4.setOnClickListener {
            message("")
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    onClick("saved")
                }
                .setNegativeButton("No") { dialog, id ->
                    message("Not saved.")
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Save Alert")
            alert.show()
        }
    }
}
