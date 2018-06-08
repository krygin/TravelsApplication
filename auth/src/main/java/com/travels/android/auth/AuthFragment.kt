package com.travels.android.auth

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.travels.android.auth.di.DaggerAuthComponent
import com.travels.android.base.di.ApplicationProvider
import kotlinx.android.synthetic.main.fragment_auth.*
import com.travels.android.base.TravelsApplication
import com.travels.android.base.domain.Response
import javax.inject.Inject

class AuthFragment : Fragment(), ApplicationProvider {
    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory

    private lateinit var authViewModel: AuthViewModel

    private var onAuthenticationResultListener: OnAuthenticationResultListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onAuthenticationResultListener = context as OnAuthenticationResultListener
        DaggerAuthComponent.builder().inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProviders.of(this, authViewModelFactory).get(AuthViewModel::class.java)
        authViewModel.vkAuthLiveData
                .observe(this, Observer {
                    when (it) {
                        is Response.Loading -> processLoading()
                        is Response.Success -> processSuccess(it.data)
                        is Response.Failure -> processFailure(it.throwable)
                    }
                })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vkAuthButton.setOnClickListener {
            val url = "https://oauth.vk.com/authorize?client_id=6368095&display=mobile&redirect_uri=http://oauth_callback&scope=friends&response_type=code&v=5.71"
            val intent = Intent(context, OAuthWebViewActivity::class.java)
                    .putExtra(EXTRA_AUTHORIZATION_CODE_URL, url)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {
                val code = data?.getStringExtra(EXTRA_CODE)
                authViewModel.vkAuth(checkNotNull(code))
            }
        }
    }

    private fun processFailure(throwable: Throwable) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun processSuccess(authResult: AuthResult) {
        onAuthenticationResultListener?.onSuccess(authResult)
    }

    private fun processLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDetach() {
        onAuthenticationResultListener = null
        super.onDetach()
    }

    override fun provideApp(): TravelsApplication = activity?.application as TravelsApplication
}

interface OnAuthenticationResultListener {
    fun onSuccess(authResult: AuthResult)
    fun onFailure()
}