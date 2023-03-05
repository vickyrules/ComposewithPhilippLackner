package com.compose_philipplackner.data

import androidx.annotation.BinderThread
import androidx.annotation.DrawableRes
import com.compose_philipplackner.R

data class BottomNavigationBarItem(
    val label : String,
    @DrawableRes val icon : Int,
)


val bottomItemList: List<BottomNavigationBarItem> = listOf(
    BottomNavigationBarItem("Home", R.drawable.ic_home),
    BottomNavigationBarItem("Meditate", R.drawable.ic_bubble),
    BottomNavigationBarItem("Sleep", R.drawable.ic_moon),
    BottomNavigationBarItem("Music", R.drawable.ic_music),
    BottomNavigationBarItem("Profile", R.drawable.ic_profile),
)