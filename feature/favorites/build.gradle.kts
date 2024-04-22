plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.library.compose)
    alias(libs.plugins.recipe.android.feature)
}

android {
    namespace = "com.rocky.feature.favorites"
}

dependencies {

    implementation(projects.data.favorites)
    implementation(projects.core.ui)

    // implement the :core:testing module later
}