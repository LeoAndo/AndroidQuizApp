package com.leoleo.androidapptemplate.ui

interface Destinations {
    val routeName: String
}

sealed interface TopDestinations : Destinations {
    object MainRoute : TopDestinations {
        override val routeName: String = "main"
    }

    object CompletedQuizRoute : TopDestinations {
        override val routeName: String = "completed_quiz"
    }
}