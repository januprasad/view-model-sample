package com.example.view_model_sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.view_model_sample.R.id

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
//    if(savedInstanceState==null)
      loadDummyFragment()
  }

  private fun loadDummyFragment() {
    val fragment = DummyFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(id.container, fragment)
        .commit()
  }
}
