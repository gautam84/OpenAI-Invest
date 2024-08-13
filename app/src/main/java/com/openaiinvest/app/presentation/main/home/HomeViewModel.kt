package com.openaiinvest.app.presentation.main.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openaiinvest.app.common.Response
import com.openaiinvest.app.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _homeState = mutableStateOf(
        HomeState()
    )
    val homeState: State<HomeState> = _homeState

    init {

        viewModelScope.launch {
            mainRepository.getUserData().collect { response ->
                when (response) {
                    is Response.Success -> {
                        _homeState.value = homeState.value.copy(
                            shares = response.data!!.shares,
                            tokens = response.data.tokens

                        )

                        Log.d("Tag",response.toString())

                    }
                    is Response.Error -> {
                        Log.d("Tag",response.message.toString())

                    }
                    is Response.Loading -> {}
                }
            }
        }
    }

}