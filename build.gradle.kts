plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "ru.ought"
version = "1.0-SNAPSHOT"

kotlin {
    js {
        browser()
        useEsModules()
        binaries.executable()
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jsMain.dependencies {
            implementation(kotlinWrappers.react)
            implementation(kotlinWrappers.reactDom)
            implementation(kotlinWrappers.emotion.react)
            implementation(kotlinWrappers.emotion.styled)
        }
    }
}
