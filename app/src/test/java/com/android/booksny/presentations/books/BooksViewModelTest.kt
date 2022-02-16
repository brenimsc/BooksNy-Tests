package com.android.booksny.presentations.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.booksny.data.ResultBooks
import com.android.booksny.data.model.Book
import com.android.booksny.data.repository.BooksRepositoryInterface
import com.android.booksny.presentation.books.BooksViewModel
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi // ou assim quando nao esta usando nenhuma anotacao ao iniciar
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() //indicando que vai rodar na JVM, em vez da MainThread

    @Mock
    private lateinit var booksObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperObserver: Observer<Pair<Int, String?>>

    private lateinit var viewModel: BooksViewModel

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this) //para inicializar os mocks quando tiver utilizando alguma anotacao na classe ante de inicar
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `quando viewmodel getBooks for sucesso entao sets booksLiveData`() {
        val books  = listOf<Book>(
            Book("Title 1", "Author 1", "Description 1"),
            Book("Title 1", "Author 1", "Description 1")
        )
        val resultSucess = MockRepository(ResultBooks.Sucess(books))
        viewModel = BooksViewModel(resultSucess)
        viewModel.books.observeForever(booksObserver) // para ver se foi chamado, assim que o viewModel chamar no metodo
        viewModel.viewFlipper.observeForever(viewFlipperObserver) // para ver se foi chamado


        viewModel.getBooks()

        verify(booksObserver).onChanged(books)
        verify(viewFlipperObserver).onChanged(Pair(1, null))

    }

    @Test
    fun `quando viewmodel getBooks for error entao sets viewFliper`() {
        val result = ResultBooks.Error("Deu erro")
        val resultError = MockRepository(result)
        viewModel = BooksViewModel(resultError)


        viewModel.viewFlipper.observeForever(viewFlipperObserver)

        viewModel.getBooks()

        verify(viewFlipperObserver).onChanged(Pair(2, result.erro)) //verificar se o valor final foi esse
    }


}

class MockRepository(private val result: ResultBooks) : BooksRepositoryInterface {

    override suspend fun getBooks(): ResultBooks {
        return result
    }

}