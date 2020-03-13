package uk.co.ergun.polat.jetpackslearning.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import uk.co.ergun.polat.jetpackslearning.R
import uk.co.ergun.polat.jetpackslearning.databinding.FragmentDetailBinding
import uk.co.ergun.polat.jetpackslearning.model.Animal
import uk.co.ergun.polat.jetpackslearning.model.AnimalPalette


class DetailFragment : Fragment() {

    var animal: Animal? = null

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }

        animal?.imageUrl?.let {
            setUpBackgroundColour(it)
        }

        dataBinding.animal = animal
    }

    private fun setUpBackgroundColour(url: String) {
        Glide.with(this).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
            }

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                Palette.from(resource).generate { palette ->
                    val intColour = palette?.lightMutedSwatch?.rgb ?: 0
                    dataBinding.palette = AnimalPalette(intColour)
                }
            }
        })
    }
}