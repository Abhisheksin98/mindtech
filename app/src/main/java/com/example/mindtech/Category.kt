package com.example.mindtech

sealed class Category(val name: String) {
    object Cars : Category("Cars")
    object Rivers : Category("Rivers")
    object Fruits : Category("Fruits")
    object Forests : Category("Forests")
    object Cities : Category("Cities")
}