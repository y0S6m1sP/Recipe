plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.library.compose)
    alias(libs.plugins.recipe.android.feature)
}

android {
    namespace = "com.rocky.feature.detail"

}

dependencies {

    implementation(projects.core.data)
    implementation(projects.core.ui)
}