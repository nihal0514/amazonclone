package com.example.amazonclone.ui.signin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.amazonclone.R
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.login.LoginRequest
import com.example.amazonclone.ui.home.HomeActivity
import com.example.amazonclone.viewModel.LoginViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var btn_login: AppCompatButton
    private lateinit var loginRequest: LoginRequest

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)


        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject1(this)
        loginViewModel = ViewModelProvider(this, mainViewModelFactory)[LoginViewModel::class.java]

        email= view.findViewById<View>(R.id.email_et_login) as EditText
        password= view.findViewById<View>(R.id.password_et_login) as EditText
        btn_login= view.findViewById<View>(R.id.btn_sign_login) as AppCompatButton

        btn_login.setOnClickListener{
            loginRequest= LoginRequest()
            loginRequest.email= email.text.toString()
            loginRequest.password= password.text.toString()
            loginViewModel.login(loginRequest)
            observeLoginModel();
            val intent = Intent (getActivity(), HomeActivity::class.java)

            getActivity()?.startActivity(intent)
        }

        return view
    }

    private fun observeLoginModel() {
        loginViewModel.loginResponseLiveData.observe(viewLifecycleOwner){login ->
            login.let {

                var editor = sharedPreferences?.edit()
                editor?.putString("token",it.token)
                editor?.putString("name",it.user?.name)
                editor?.apply()
            }
        }
    }
}