plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.hilt)
}

android {
    namespace = "com.rocky.data.detail"
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.network)
}