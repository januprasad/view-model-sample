package com.example.view_model_sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.view_model_sample.api.PostOfficeResponse
import kotlinx.android.synthetic.main.fragment_dummy.button_pincode
import kotlinx.android.synthetic.main.fragment_dummy.result

class DummyFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_dummy, container, false)
  }

  private var viewModel: DummyViewModel? = null
  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    viewModel = ViewModelProvider(requireActivity()).get(DummyViewModel::class.java)

    button_pincode.setOnClickListener {
      apiCall()

    }
  }

  private fun observeResponse() {
    // Show the ViewModel property's value in a TextView
    viewModel?.postOfficeResponse?.observe(requireActivity(), Observer { response ->
      if (response != null) {
        Log.w("Response",response[0].Message)
        buildTextViewString(response)
      }
    })

  }

  private fun apiCall() {
    viewModel?.postOfficeAPI()
    observeResponse()
  }

  private fun buildTextViewString(response: PostOfficeResponse) {
    result.text = "${response[0].PostOffice[0].Name}~${response[0].PostOffice[0].Pincode}"
  }

  companion object {
    @JvmStatic fun newInstance() = DummyFragment()
  }


}