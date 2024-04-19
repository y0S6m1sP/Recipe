plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.library.compose)
    alias(libs.plugins.recipe.android.feature)
}

android {
    namespace = "com.rocky.feature.home"
}

dependencies {

    implementation(projects.data.home)
    implementation(projects.core.ui)

    // implement the :core:testing module later
}