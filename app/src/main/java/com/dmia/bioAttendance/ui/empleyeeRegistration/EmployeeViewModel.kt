package com.dmia.bioAttendance.ui.empleyeeRegistration

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmia.bioAttendance.repositry.UsersRepositry
import com.dmia.bioAttendance.utils.helper.NetworkHelper
import com.dmia.bioAttendance.helper.NetworkResult
import com.dmia.bioAttendance.model.EmployeeNameSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject


@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: UsersRepositry,
    private var networkHelper: NetworkHelper,
    var app: Application,
) : ViewModel() {

    var employeSearchResponse = MutableLiveData<NetworkResult<ResponseBody>>()
    var isloading = ObservableField(false)


    fun searchUsers(employeeid: String) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                repository.getEmployeeSearchData(employeeid).collect { values ->
                    employeSearchResponse.postValue(values)
                }
            } else {
//                app.showToast("No Internet")
            }
        }
    }

}