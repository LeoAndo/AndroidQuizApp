package com.leoleo.androidapptemplate.ui

interface Destinations {
    val routeName: String
}

sealed interface TopDestinations : Destinations {
    object TopRoute : TopDestinations {
        override val routeName: String = "top"
    }

    object MainRoute : TopDestinations {
        override val routeName: String = "main"
    }

    object CompletedTasksRoute : TopDestinations {
        override val routeName: String = "completed_tasks"
    }
}