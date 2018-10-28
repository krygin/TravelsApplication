import com.travels.android.build_src.Dependencies


plugins {
    id("com.travels.kotlin")
}

dependencies {
    api(project(":data"))
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.rxKotlin)
}
