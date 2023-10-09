package com.tc.mytest.view.beerlist

import com.tc.mytest.data.repository.Repository
import com.tc.mytest.view.beerdetail.BeerDetailsViewModel
import org.junit.Assert.*
import org.mockito.Mock
import com.tc.mytest.data.model.beer.BeerItemModel
import com.tc.mytest.data.remote.ApiRequest
import com.tc.mytest.data.repository.RepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BeerScreenViewModelTest{




        private lateinit var repository: Repository
        private val apiRequest: ApiRequest = mockk()

        @Before
        fun setup() {
            Dispatchers.setMain(Dispatchers.Unconfined)
            repository = RepositoryImpl(apiRequest)
        }

        @After
        fun tearDown() {
            Dispatchers.resetMain()
        }

        @Test
        fun `test getBeers`() = runBlockingTest {
            // Mock data for testing
            val mockBeers = listOf(
                BeerItemModel(id = 1, name = "Beer 1"),
                BeerItemModel(id = 2, name = "Beer 2")
            )

            // Mock the repository call
            coEvery { apiRequest.getBeers() } returns mockBeers


            // Call the Repository function
            val result = repository.getBeers()

            // Assert the result
            assertEquals(mockBeers, result)
        }

        @Test
        fun `test getBeerItem`() = runBlockingTest {
            // Mock data for testing
            val beerId = 1
            val mockBeerItem = BeerItemModel(id = beerId, name = "Mock Beer")

            // Mock the repository call
            coEvery { apiRequest.getBeerItem(beerId) } returns listOf(mockBeerItem)

            // Call the Repository function
            val result = repository.getBeerItem(beerId)

            // Assert the result
            assertEquals(listOf(mockBeerItem), result)
        }
    }
