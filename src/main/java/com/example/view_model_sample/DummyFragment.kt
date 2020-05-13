package com.example.view_model_sample

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.view_model_sample.api.PostOfficeDTO
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

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    button_pincode.setOnClickListener {
      apiCall()
    }
  }

  private fun apiCall() {

    var viewModel = activity?.run {
      ViewModelProviders.of(this)[DummyViewModel::class.java]
    } ?: throw Exception("Invalid Activity")

    postOfficeResponseObserver(viewModel.postOfficeAPI())

  }

  private fun postOfficeResponseObserver(postOfficeAPI: MutableLiveData<PostOfficeResponse>) {
    activity?.let {
      postOfficeAPI.observe(it, Observer { response ->
        if (response != null) {
        Log.w("Response",response[0].Message)
         buildTextViewString(response)
        }
      })
    }
  }

  private fun buildTextViewString(response: PostOfficeResponse) {
    result.text = """${response[0].PostOffice[0].Name}${response[0].PostOffice[0].Pincode}"""
  }
  /* private fun postOfficeResponseObserver(postOfficeAPI: MutableLiveData<PostOfficeDTO>) {
    activity?.let {
      postOfficeAPI.observe(it, Observer { response ->
        if (response != null) {
          Log.w("Response",response.Message)
        }
      })
    }
  }*/

  companion object {
    @JvmStatic fun newInstance() = DummyFragment()
  }


}