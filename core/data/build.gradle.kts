plugins {
    alias(libs.plugins.recipe.android.library)
    alias(libs.plugins.recipe.android.hilt)
}

android {
    namespace = "com.rocky.data"
}

dependencies {

    api(projects.core.network)

    // remove model dependency after implement local db
    implementation(projects.core.model)

}