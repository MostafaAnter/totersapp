package app.anter.feature_characters_list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import app.anter.core.util.NetworkHelper
import app.anter.core_data.local.database.daos.CharacterItemDao
import app.anter.core_data.remote.responses.charactersResponse.HeroObject
import app.anter.core_data.repository.AppRepository
import app.anter.core_data.repository.CharactersSearchRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class GamesSearchViewModel @Inject constructor(
    private val remoteRepository: AppRepository,
    private val networkHelper: NetworkHelper,
    private val characterItemDao: CharacterItemDao
) : ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<HeroObject>>? = null

    fun searchRepo(queryString: String?): Flow<PagingData<HeroObject>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<HeroObject>> = getDataFromPagerRepo(queryString)
        currentSearchResult = newResult
        return newResult
    }

    // do search query and get data from pager source
    private fun getDataFromPagerRepo(query: String?): Flow<PagingData<HeroObject>>{
         return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = CharactersSearchRemoteMediator(
                query,
                remoteRepository,
                networkHelper,
                characterItemDao
            )
        ) {
            characterItemDao.pagingAllDataSource()
        }.flow.cachedIn(viewModelScope)
    }

}