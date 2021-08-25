package net.yakuraion.mangakko.genres_impl.ui.genres.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.yakuraion.mangakko.core_repositories.GenresRepository
import net.yakuraion.mangakko.core_testutils.MainCoroutineRule
import net.yakuraion.mangakko.core_testutils.TestDispatchers
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.junit.*
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GenresViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var genresViewModel: GenresViewModel

    @Mock
    private lateinit var genresRepository: GenresRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        genresViewModel = GenresViewModel(SavedStateHandle(), TestDispatchers(), genresRepository)
    }

    @Test
    fun checkGenresLiveData() = runBlockingTest {
        whenever(genresRepository.getGenres()) doReturn getMockGenres()
        genresViewModel.genresLiveData.observeForever { }
        assertEquals(getMockGenres(), genresViewModel.genresLiveData.value)
    }

    private fun getMockGenres(): List<String> = List(5) { "genre $it" }
}
