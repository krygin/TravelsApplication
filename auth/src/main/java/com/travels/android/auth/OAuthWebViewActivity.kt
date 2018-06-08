package com.travels.android.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.*
import kotlinx.android.synthetic.main.activity_oauth_web_view.*

class OAuthWebViewActivity : AppCompatActivity() {


    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oauth_web_view)
        url = intent.getStringExtra(EXTRA_AUTHORIZATION_CODE_URL)
        webView.settings.setAppCacheEnabled(false)
        webView.settings.saveFormData = false
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return if (request?.url?.toString()?.startsWith("http://oauth_callback") == true) {
                    val code = request.url.getQueryParameter("code")
                    val result = Intent().apply {
                        putExtra(EXTRA_CODE, code)
                    }
                    setResult(Activity.RESULT_OK, result)
                    finish()
                    true
                } else super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        webView.loadUrl(url)
    }

    override fun onDestroy() {
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookies {  }
        super.onDestroy()
    }

}

const val EXTRA_AUTHORIZATION_CODE_URL = "EXTRA_AUTHORIZATION_CODE_URL"

const val EXTRA_CODE = "EXTRA_CODE"