package prueba.movil.prueba.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import io.reactivex.Observable
import org.jetbrains.anko.toast
import kotlin.reflect.KClass

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
fun <T : ViewModel> AppCompatActivity.buildViewModel(factory: ViewModelProvider.Factory, kClass: KClass<T>): T
        = ViewModelProviders.of(this, factory).get(kClass.java)

fun <T : ViewModel> Fragment.buildViewModel(factory: ViewModelProvider.Factory, kClass: KClass<T>): T
        = ViewModelProviders.of(this, factory).get(kClass.java)

fun AppCompatActivity.putFragment(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
}