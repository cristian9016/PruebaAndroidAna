package prueba.movil.prueba.util

import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import io.reactivex.Observable
import org.jetbrains.anko.toast

/**
 * Created by Ana Marin on 26/11/2017.
 */
fun ViewGroup.inflate(@LayoutRes layout:Int) = LayoutInflater.from(context).inflate(layout, this, false)

fun EditText.text():String = text.toString()

fun AppCompatActivity.validateForm(message: Int,
                                   vararg fields: String) : Observable<List<String>>
        = Observable.create<List<String>>{
    if(fields.contains("")) toast(message)
    else it.onNext(fields.toList())
    it.onComplete()
}