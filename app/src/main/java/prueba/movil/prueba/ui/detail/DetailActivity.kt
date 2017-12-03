package prueba.movil.prueba.ui.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import prueba.movil.prueba.R
import prueba.movil.prueba.data.Global
import prueba.movil.prueba.data.model.Item
import prueba.movil.prueba.databinding.ActivityDetailBinding
import prueba.movil.prueba.di.Injectable
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), Injectable {

    lateinit var binding: ActivityDetailBinding
    lateinit var item: Item

    @Inject
    lateinit var global:Global

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.item = global.selected
    }

    override fun onResume() {
        super.onResume()
    }
}
