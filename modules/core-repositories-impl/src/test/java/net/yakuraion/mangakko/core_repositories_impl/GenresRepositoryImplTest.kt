package net.yakuraion.mangakko.core_repositories_impl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.yakuraion.mangakko.core_network.GenresApi
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.junit.*
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GenresRepositoryImplTest {

    private lateinit var genresRepository: GenresRepositoryImpl

    @Mock
    private lateinit var genresApi: GenresApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        genresRepository = GenresRepositoryImpl(genresApi)
    }

    @Test
    fun getGenres() = runBlockingTest {
        whenever(genresApi.getGenres()) doReturn getMockGenres()
        assertEquals(getMockGenres(), genresRepository.getGenres())
    }

    private fun getMockGenres(): List<String> = List(5) { "genre $it" }
}
