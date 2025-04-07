import org.gradle.kotlin.dsl.libs

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "dmos.example.biometricclient"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "dmos.example.biometricclient"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        // GitHub Packages repository for the SDK dependency
        maven {
            url = uri("https://maven.pkg.github.com/ostrovskyid/biometric-sdk")
            credentials {
                username = project.findProperty("gpr.user")?.toString() ?: System.getenv("GPR_USER")
                password = project.findProperty("gpr.key")?.toString() ?: System.getenv("GPR_KEY")
            }
        }
    }
}

dependencies {
    implementation(libs.biometricSdk)

    implementation(libs.androidx.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

    // Dagger 2
    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)

    // Lifecycle components (Java versions)
    implementation(libs.livedata)
    implementation(libs.viewmodel)

    // Retrofit (if needed)
    implementation(libs.retrofit)
    implementation(libs.gson)

    testImplementation(libs.junit)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${libs.versions.junit.get()}")

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}