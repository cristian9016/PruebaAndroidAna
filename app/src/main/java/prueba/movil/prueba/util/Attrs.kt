package prueba.movil.prueba.util

import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Ana Marin on 26/11/2017.
 */
@BindingAdapter("app:imgUrl")
fun setImageUrl(img: ImageView, url:String){
    Picasso.with(img.context)
            .load(Uri.parse(url))
            .into(img)
}