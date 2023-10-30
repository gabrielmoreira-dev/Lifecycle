package br.edu.scl.ifsp.sdm.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.edu.scl.ifsp.sdm.lifecycle.databinding.ActivityAnotherBinding

class AnotherActivity : AppCompatActivity() {
    private val activityAnotherBinding: ActivityAnotherBinding by lazy {
        ActivityAnotherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityAnotherBinding.root)

        activityAnotherBinding.apply {
            setSupportActionBar(toolbarIn.toolbar)
        }
        supportActionBar?.subtitle = getString(R.string.another)
        Log.v(getString(R.string.app_name), "Another - onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.v(getString(R.string.app_name), "Another - onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.v(getString(R.string.app_name), "Another - onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.v(getString(R.string.app_name), "Another - onPause()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(getString(R.string.app_name), "Another - onRestart()")
    }

    override fun onStop() {
        super.onStop()
        Log.v(getString(R.string.app_name), "Another - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(getString(R.string.app_name), "Another - onDestroy()")
    }
}