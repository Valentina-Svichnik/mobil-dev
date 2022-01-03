package com.example.lesson4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.example.lesson4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskCallbacks {

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    companion object {
        const val RESULT = "RESULT"
    }

    private lateinit var binding: ActivityMainBinding
    private var fragment: MyFragment? = null
    private var myResult: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.getInt(RESULT)?.let {
            Log.d("MY TAG", "RESTORE STATE = $it")
        }

        val fm = supportFragmentManager
        val oldFragment = fm.findFragmentByTag(MyFragment.TAG)
        if (oldFragment == null) {
            fragment = MyFragment()
            fm
                .beginTransaction()
                .add(fragment!!, MyFragment.TAG)
                .commit()
        } else {
            fragment = oldFragment as MyFragment
        }

        fragment?.startTask()
    }

    private fun update(message: String) {
        binding.items.layoutManager = verticalLinearLayoutManager
        binding.items.adapter = MyAdapter(MyHolder.createList(message))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(RESULT, myResult)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fragment?.cancelTask()
    }

    override fun onPreExecuted() {
    }

    override fun onCanceled() {
        Log.d("MY TAG", "CANCELLED")
    }

    override fun onPostExecute(item: String) {
        myResult = item
        Log.d("MY TAG", "MESSAGE =  $item")
        update(item)
    }
}
