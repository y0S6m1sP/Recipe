plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.library.compose)
}

android {
    namespace = "com.rocky.core.ui"
}

dependencies {
    api(projects.core.model)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    // implement the :core:testing module later
}