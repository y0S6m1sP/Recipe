plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.library.compose)
}

android {
    namespace = "com.rocky.feature.home"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    // implement the :core:testing module later
}