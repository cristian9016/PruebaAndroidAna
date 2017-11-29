package prueba.movil.prueba.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject
import prueba.movil.prueba.R
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.databinding.TemplateItemBinding
import prueba.movil.prueba.di.ActivityScope
import prueba.movil.prueba.di.FragmentScope
import prueba.movil.prueba.util.inflate
import javax.inject.Inject

/**
 * Created by Ana Marin on 26/11/2017.
 */
@ActivityScope
class ItemAdapter @Inject constructor(): RecyclerView.Adapter<ItemAdapter.MovieHolder>(){

    val clickItem = PublishSubject.create<Movie>()

    var items: List<Item> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.clickItem= clickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder
        = MovieHolder(parent.inflate(R.layout.template_item))

    override fun getItemCount(): Int = items.size

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: TemplateItemBinding = DataBindingUtil.bind(itemView)

    }
}