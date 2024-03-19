import com.rocky.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("recipe.android.library")
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())

                add("testImplementation", kotlin("test"))

                add("androidTestImplementation", kotlin("test"))
            }
        }
    }
}