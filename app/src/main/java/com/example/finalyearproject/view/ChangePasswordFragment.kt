package com.example.finalyearproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.finalyearproject.R
import com.example.finalyearproject.model.RequestChangePassword
import com.example.finalyearproject.viewmodel.ChangePasswordViewModel
import com.example.finalyearproject.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_change_password.*


class ChangePasswordFragment : Fragment() {

    private lateinit var viewModel : ChangePasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ChangePasswordViewModel::class.java)

        resetPageButton.setOnClickListener {
            val request = RequestChangePassword(resetPageToken.text.trim().toString(),resetPageNewPassword.text.trim().toString())
            viewModel.resetPassword(request,resetPageToken.text.trim().toString())
        }
        subcribeObserver()
    }

    fun subcribeObserver(){

        viewModel.resetPageResponse_.observe(viewLifecycleOwner, Observer {
            when(it.status_code){

                "SUCCESS" -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    val action = view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_changePasswordFragment2_to_loginFragment)
                    }
                }

                "FAILED" -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

            }
        })


    }

}