package com.example.webviewtest

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebView.setWebContentsDebuggingEnabled(true);

        val button: Button = findViewById(R.id.goButton)
        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.webViewClient = MyWebClient()

        button.setOnClickListener {
            hideKeyboard()
            val textInput = findViewById<EditText>(R.id.url_input)
            val url = textInput.text.toString()
            myWebView.loadUrl(url)
        }
        myWebView.loadUrl("https://www.nordstrom.com/browse/services/personal-stylists?jid\\u003dj011912-13889")
    }

    fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm?.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}

class MyWebClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }
}
