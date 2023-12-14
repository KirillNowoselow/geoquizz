package com.example.geoquiz
import androidx.annotation.*

data class Question(@StringRes val textResId: Int, val answer: Boolean){
}