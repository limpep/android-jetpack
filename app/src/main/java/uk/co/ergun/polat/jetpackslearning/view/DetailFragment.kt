package uk.co.ergun.polat.jetpackslearning.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_detail.*

import uk.co.ergun.polat.jetpackslearning.R
import uk.co.ergun.polat.jetpackslearning.model.Animal
import uk.co.ergun.polat.jetpackslearning.util.getProgressDrawable
import uk.co.ergun.polat.jetpackslearning.util.loadImage


class DetailFragment : Fragment() {

    var animal: Animal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }


        context?.let {
            animalImage.loadImage(animal?.imageUrl, getProgressDrawable(it))
            animalName.text = animal?.name
            animalLocation.text = animal?.location
            animalLifeSpan.text = animal?.lifeSpan
            animalDiet.text = animal?.diet
        }

    }
}