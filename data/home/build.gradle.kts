plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.hilt)
}

android {
    namespace = "com.rocky.data.home"
}

dependencies {
    api(projects.core.common)
    api(projects.core.network)
}