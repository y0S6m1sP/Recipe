plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.library.compose)
    alias(libs.plugins.recipe.android.feature)
}

android {
    namespace = "com.rocky.feature.settings"
}

dependencies {

    implementation(libs.androidx.core.ktx)

    // implement the :core:testing module later
}