plugins {
    id ("com.android.library")
    id ("kotlin-android")

    // fot dagger until now
    kotlin("kapt")

    //for hilt
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildTypes.forEach {
        it.buildConfigField("String", "API_BASE_URL", "\"https://gateway.marvel.com/\"")
        it.buildConfigField("String", "PUBLIC_API_KEY", "\"a4ed1bd080b830c92a687cf3be2ed797\"")
        it.buildConfigField("String", "PRIVATE_API_KEY", "\"f777157d15b93664728420bf06312cef2727777f\"")
    }
    // enable data binding inside module
    dataBinding.apply {
        @Suppress("DEPRECATION")
        isEnabled = true
    }

    // solve the problem of run test cases
    packagingOptions {
        // option 1
        pickFirst ("**/META-INFMANIFEST.MF")
        pickFirst ("META-INF/*")
    }
}

dependencies {

    implementation(project(":core"))

    implementation (AppDependencies.kotlin_stdlib)
    implementation (AppDependencies.core_ktx)
    implementation (AppDependencies.androidx_appcompat)
    implementation (AppDependencies.google_material)

    // region dagger
    implementation (AppDependencies.dagger_hilt)
    kapt (AppDependencies.dagger_kapt)
    // end region

    // region retrofit network calls
    implementation (AppDependencies.retrofit)
    implementation (AppDependencies.retrofit_converter)
    // for support android 4
    implementation (AppDependencies.okHttp_interceptor)
    implementation (AppDependencies.okHttp){
        version{
            strictly(Versions.okHttpVersion)
        }
    }
    // endregion

    // region Moshi very important to generate adapters converters
    implementation (AppDependencies.moshi)
    kapt(AppDependencies.moshi_kapt)
    // endregion

    // region Coroutine
    implementation (AppDependencies.coroutine_android)
    implementation (AppDependencies.coroutine_core)
    //endregion

    // coil image loader
    implementation(AppDependencies.coil)

    //region database
    implementation(AppDependencies.room_database)
    implementation(AppDependencies.room_database_ktx)
    kapt(AppDependencies.room_database_kapt)
    //endregion

    // paging lib
    implementation(AppDependencies.paging_lib)

    //region unit test and ui test
    //test with jvm
    testImplementation (AppDependencies.junit)
    testImplementation (AppDependencies.truth_test)

    //test with android device
    androidTestImplementation (AppDependencies.junit)
    androidTestImplementation (AppDependencies.truth_test)
    androidTestImplementation (AppDependencies.junit_test)
    androidTestImplementation (AppDependencies.test_coroutine)
    //for support app context inside test method
    androidTestImplementation (AppDependencies.test_arch_core)
    androidTestImplementation (AppDependencies.espresso_core)
    //endregion
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}