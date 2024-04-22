plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.hilt)
    alias(libs.plugins.googleDevtoolsKsp)
}

android {
    namespace = "com.rocky.core.database"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(projects.core.model)

    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}