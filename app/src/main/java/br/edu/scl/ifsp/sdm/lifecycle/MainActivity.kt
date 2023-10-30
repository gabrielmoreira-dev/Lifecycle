package br.edu.scl.ifsp.sdm.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import br.edu.scl.ifsp.sdm.lifecycle.databinding.ActivityMainBinding
import br.edu.scl.ifsp.sdm.lifecycle.databinding.TilePhoneBinding
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var filledChars = 0

    companion object {
        const val FILLED_CHARS = "FILLED_CHARS"
        const val PHONES = "PHONES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {
            setSupportActionBar(toolbarIn.toolbar)

            nameEt.doAfterTextChanged {
                updateCounter(++filledChars)
            }

            addPhoneBt.setOnClickListener{
                val tilePhoneBinding = TilePhoneBinding.inflate(layoutInflater)
                phonesLl.addView(tilePhoneBinding.root)
            }

            openAnotherActivityBt.setOnClickListener {
                startActivity(Intent(this@MainActivity, AnotherActivity::class.java))
            }
        }
        supportActionBar?.subtitle = getString(R.string.main)

        Log.v(getString(R.string.app_name), "Main - onCreate()")

        Thread {
            sleep(3000)
            runOnUiThread {
                activityMainBinding.nameEt.setText(R.string.sdm)
            }
        }.start()
    }

    override fun onStart() {
        super.onStart()
        Log.v(getString(R.string.app_name), "Main - onStart()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(FILLED_CHARS, filledChars)

        val phones = mutableListOf<String>()
        activityMainBinding.phonesLl.children.forEachIndexed { index, view ->
            if (index != 0) {
                (view as EditText).text.toString().let {
                    phones.add(it)
                }
            }
        }
        outState.putStringArray(PHONES, phones.toTypedArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.apply {
            getInt(FILLED_CHARS, 0).let {
                filledChars = it
                updateCounter(it)
            }
            getStringArray(PHONES)?.forEach {
                TilePhoneBinding.inflate(layoutInflater).apply {
                    root.setText(it)
                    activityMainBinding.phonesLl.addView(root)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.v(getString(R.string.app_name), "Main - onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.v(getString(R.string.app_name), "Main - onPause()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(getString(R.string.app_name), "Main - onRestart()")
    }

    override fun onStop() {
        super.onStop()
        Log.v(getString(R.string.app_name), "Main - onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(getString(R.string.app_name), "Main - onDestroy()")
    }

    private fun updateCounter(value: Int) {
        "${getString(R.string.filled_chars)} $value".also {
            activityMainBinding.filledCharsTv.text = it
        }
    }
}