package qqq.qqq.presentation

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class SampleRxJava(val context: Context) {

    private var TAG = "MainActivity"
    private var handler = Handler(Looper.getMainLooper())


    fun startRStream() {
        Thread {
            val myObservable = getObservable()
            val myObserver = getObserver()
            myObservable
                .subscribe(myObserver)
        }.start()
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(s: String) {
                handler.post {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, "onNext: $s")
                Thread.sleep(2000)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError:" + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }

    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }
}