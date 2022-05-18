package qqq.qqq.presentation

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import java.util.concurrent.TimeUnit

class SampleRxJava(val context: Context) {

    private var TAG = "MainActivity"
    private var handler = Handler(Looper.getMainLooper())


    fun startRStream() {
        val myObservable = getObservable()
        val myObserver = getObserver()
        myObservable
            .delay(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(myObserver)
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
            }

            override fun onNext(s: String) {
                handler.post {
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, "onNext: $s")
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