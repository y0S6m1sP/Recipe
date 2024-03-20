plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.hilt)
}

android {
    namespace = "com.rocky.core.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(projects.core.model)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.square.moshi.ktx)
}