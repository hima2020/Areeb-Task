package org.areeb.technicalTask.utils

sealed class GeneralStates {
    object Loading : GeneralStates()
//    object NoConnection : GeneralStates()

    data class Success<T>(val data: T) : GeneralStates()
    data class Failed(val error: String = "") : GeneralStates()
}
