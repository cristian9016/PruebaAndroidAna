package prueba.movil.prueba.ui.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import prueba.movil.prueba.R
import prueba.movil.prueba.data.model.Movie
import prueba.movil.prueba.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        movie = intent.extras.getParcelable<Movie>("movie")
        binding.item = movie
    }

    override fun onResume() {
        super.onResume()
    }
}
