package com.tc.mytest.view.beerdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.mytest.data.model.beer.BeerItemModel
import com.tc.mytest.data.model.beer.BoilVolumeModel
import com.tc.mytest.data.model.beer.FermentationModel
import com.tc.mytest.data.model.beer.IngredientsModel
import com.tc.mytest.data.model.beer.MethodModel
import com.tc.mytest.data.model.beer.TempModel
import com.tc.mytest.data.model.beer.VolumeModel
import com.tc.mytest.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class BeerDetailsViewModel @Inject constructor(
//    val repository: Repository
//) : ViewModel() {
//
//    private val _beerDetails = MutableLiveData<BeerItemModel>()
//    val beerDetails: LiveData<BeerItemModel> = _beerDetails
//
//    fun getBeerItem(beerId: Int) {
//        viewModelScope.launch {
//            val result = repository.getBeerItem(beerId)[0]
//            _beerDetails.value = result
//        }
//    }
//
//}

@HiltViewModel
class BeerDetailsViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _beerDetails = MutableLiveData<BeerItemModel>()
    val beerDetails: LiveData<BeerItemModel> = _beerDetails

    suspend fun getBeerItem(beerId: Int) {
        val beerItem = repository.getBeerItem(beerId).firstOrNull() // Get the beer item

        // Update the properties of the beer item (if available)
        beerItem?.let {
            val updatedBeerItem = it.copy(
                abv = 0.0,
                attenuationLevel = 0.0,
                boilVolume = BoilVolumeModel("", 0),
                brewersTips = "",
                contributedBy = "",
                description = "",
                ebc = 0,
                firstBrewed = "",
                foodPairing = emptyList(),
                ibu = 0.0,
                imageUrl = "",
                ingredients = IngredientsModel(emptyList(), emptyList(), ""),
                method = MethodModel(FermentationModel(TempModel("", 0)), ""),
                name = "",
                ph = 0.0,
                srm = 0.0,
                tagline = "",
                targetFg = 0,
                targetOg = 0.0,
                volume = VolumeModel("", 0)
            )
            _beerDetails.value = updatedBeerItem
        }
    }
}
