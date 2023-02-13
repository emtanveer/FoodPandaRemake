package com.fpremake.uiTest.memeTest

import com.fpremake.shared.FPRemakeApplication

class TestApplicationClass : FPRemakeApplication() {

  var url = "http://127.0.0.1:8080"  
 
  override fun getBaseUrl(): String {  
    return url  
  }


}