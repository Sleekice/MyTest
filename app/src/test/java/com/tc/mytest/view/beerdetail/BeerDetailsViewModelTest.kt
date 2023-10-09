package com.tc.mytest.view.beerdetail

import com.tc.mytest.data.model.beer.BeerItemModel
import com.tc.mytest.data.model.beer.BoilVolumeModel
import com.tc.mytest.data.model.beer.FermentationModel
import com.tc.mytest.data.model.beer.IngredientsModel
import com.tc.mytest.data.model.beer.MethodModel
import com.tc.mytest.data.model.beer.TempModel
import com.tc.mytest.data.model.beer.VolumeModel
import com.tc.mytest.data.repository.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class BeerDetailsViewModelTest{


    private val repository: Repository = mockk()
    private lateinit var viewModel: BeerDetailsViewModel

    private val dispatcher = StandardTestDispatcher()



    @Before
    fun setup() {

        Dispatchers.setMain(dispatcher)

        viewModel = BeerDetailsViewModel(repository)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after the test
        Dispatchers.resetMain()
    }


//
//
//    @Test
//    fun `test view model items`() = runBlocking {
//        // Mock data for testing
//        val mockBeerItem = BeerItemModel(id = 12)
//        val beerId = 12
//
//        // Mock the repository call
//        coEvery { repository.getBeerItem(beerId) } returns listOf(mockBeerItem)
//
//        // Call the ViewModel function
//        viewModel.getBeerItem(beerId)
//
//        // Assert the LiveData value
//        assertEquals(mockBeerItem, viewModel.beerDetails.value)
//    }
//


    @Test
    fun `test view model items`() = runBlocking {
        // Mock data for testing
        val mockBeerItem = BeerItemModel(id = 12)
        val beerId = 12

        // Mock the repository call
        coEvery { repository.getBeerItem(beerId) } returns listOf(mockBeerItem)

        // Call the ViewModel function
        viewModel.getBeerItem(beerId)



        // Assert the LiveData value
        val expectedBeerItem = mockBeerItem.copy(
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
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expectedBeerItem, viewModel.beerDetails.value)
    }


}