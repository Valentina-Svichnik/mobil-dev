package com.example.lesson4

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import java.util.concurrent.TimeUnit

interface TaskCallbacks {
    fun onPreExecuted()
    fun onCanceled()
    fun onPostExecute(item: String)
}

class MyFragment : Fragment() {

    companion object {
        const val TAG = "MyFragment"
    }

    private var callbacks: TaskCallbacks? = null
    private var task: MyTask? = null
    private var handler: Handler? = null
    private var number = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        startTask()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = requireActivity() as TaskCallbacks
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    fun startTask() {
        task = MyTask()
        val handlerCallback: Handler.Callback = object : Handler.Callback {

            override fun handleMessage(message: Message): Boolean {
                callbacks?.onPostExecute("${message.what}")
                return false
            }
        }
        handler = Handler(handlerCallback)
        task?.execute()
    }

    fun cancelTask() {
        if (task == null) return
        task?.cancel(true)
    }

    inner class MyTask : AsyncTask<Unit, Int, Unit>() {

        override fun onPreExecute() {
            callbacks?.onPreExecuted()
        }

        override fun doInBackground(vararg p0: Unit?) {
            Log.d("TAG", "start task")
            try {
                for (i in 1..6) {
                    publishProgress(i)
                    TimeUnit.SECONDS.sleep(2)
                    if (isCancelled) break
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        override fun onCancelled() {
            callbacks?.onCanceled()
        }

        override fun onProgressUpdate(vararg values: Int?) {
            handler?.sendEmptyMessage(number)
            number++
        }
    }
}
