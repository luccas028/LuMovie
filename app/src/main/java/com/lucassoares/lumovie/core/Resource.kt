package com.lucassoares.lumovie.core

import java.lang.Exception

// sealed lo que nos permite es retornar estados en kotlin
sealed class Resource <out T> {

    class Loading <out T> :Resource<T>()

    data class Succes<out T> (val data :T):Resource <T> ()

    data class Failure (val exception: Exception): Resource<Nothing>()


}