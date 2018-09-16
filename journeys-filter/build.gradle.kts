import com.travels.android.build_src.Dependencies

plugins {
    id("com.travels.library")
    id("kotlin-kapt")
}


dependencies {
    implementation(project(":base"))
    implementation(project(":design:date-range-picker"))
}
