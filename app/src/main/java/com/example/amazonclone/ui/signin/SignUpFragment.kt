package com.example.amazonclone.ui.signin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.amazonclone.R
import com.example.amazonclone.di.ApplicationComponent
import com.example.amazonclone.di.DaggerApplicationComponent
import com.example.amazonclone.model.login.RegisterRequest
import com.example.amazonclone.model.login.RegisterResponse
import com.example.amazonclone.ui.HomeActivity
import com.example.amazonclone.viewModel.CategoryViewModel
import com.example.amazonclone.viewModel.LoginViewModel
import com.example.amazonclone.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_sign_up.btn_register
import javax.inject.Inject

class SignUpFragment : Fragment() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btn_register: AppCompatButton
    private lateinit var registerRequest: RegisterRequest

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var applicationComponent: ApplicationComponent
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up, container, false)

        sharedPreferences = requireContext().getSharedPreferences("amazonclone", Context.MODE_PRIVATE)


        applicationComponent = DaggerApplicationComponent.factory().create(requireContext())
        applicationComponent.inject1(this)
        loginViewModel = ViewModelProvider(this, mainViewModelFactory)[LoginViewModel::class.java]



        name= view.findViewById<View>(R.id.name_et) as EditText
        email= view.findViewById<View>(R.id.email_et) as EditText
        password= view.findViewById<View>(R.id.password_et) as EditText
        btn_register= view.findViewById<View>(R.id.btn_register) as AppCompatButton


        btn_register.setOnClickListener{
            registerRequest= RegisterRequest()
             registerRequest.name= name.text.toString()
            registerRequest.email= email.text.toString()
            registerRequest.password= password.text.toString()
            loginViewModel.register(registerRequest)
            observeRegisterModel();
            val intent = Intent (getActivity(), HomeActivity::class.java)

            getActivity()?.startActivity(intent)
        }
        return view
    }

    private fun observeRegisterModel() {
        loginViewModel.registerResponseLiveData.observe(viewLifecycleOwner){register ->
            register.let {

                var editor = sharedPreferences?.edit()
                editor?.putString("token",it.token)
                editor?.putString("name",it.user?.name)
                editor?.apply()
            }
        }
    }
}