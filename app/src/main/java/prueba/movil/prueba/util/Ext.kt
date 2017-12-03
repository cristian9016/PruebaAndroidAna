package prueba.movil.prueba.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
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

inline fun <reified T : ViewModel> Fragment.buildViewModel(factory: ViewModelProvider.Factory): T
        = ViewModelProviders.of(this, factory).get(T::class.java)

fun AppCompatActivity.putFragment(container: Int, fragment: Fragment, title:Unit) {
    supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
}