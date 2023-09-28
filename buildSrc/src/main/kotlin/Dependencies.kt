import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.dataStore() {
    implementation(Dependency.dataStore)
}

fun DependencyHandler.accompanist() {
    implementation(Dependency.accompanist)
}

fun DependencyHandler.coil() {
    implementation(Dependency.coil)
}

fun DependencyHandler.paging() {
    implementation(Dependency.pagingKtx)
    implementation(Dependency.pagingCompose)
}

fun DependencyHandler.retrofit() {
    implementation(Dependency.retrofit)
    implementation(Dependency.retrofitConverter)
    implementation(Dependency.okHttp)
    implementation(Dependency.mockWebServer)
    implementation(Dependency.okHttpLoggingInterceptor)
}

fun DependencyHandler.room() {
    implementation(Dependency.room)
    implementation(Dependency.roomKTx)
    ksp(Dependency.roomKspCompiler)
}

fun DependencyHandler.coroutinesAndroid() {
    implementation(Dependency.coroutinesAndroid)
}

fun DependencyHandler.coroutinesCore() {
    implementation(Dependency.coroutinesCore)
}

fun DependencyHandler.navigation() {
    implementation(Dependency.navigation)
}

fun DependencyHandler.navigationHiltExt() {
    implementation(Dependency.navigationHiltVm)
}

fun DependencyHandler.hilt() {
    implementation(Dependency.hilt)
    kapt(Dependency.hiltKaptCompiler)
}

fun DependencyHandler.compose() {
    implementation(platform(Dependency.composeBom))
    implementation(Dependency.composeUi)
    implementation(Dependency.composeUiGraphics)
    implementation(Dependency.composeUiToolingPreview)
    implementation(Dependency.material3)
    implementation(Dependency.material)
    implementation(Dependency.extendedIcons)
    androidTestImplementation(platform(Dependency.composeBom))
    androidTestImplementation(Dependency.composeJUnit)
    debugImplementation(Dependency.composeUiTooling)
    debugImplementation(Dependency.composeTestManifest)
}

fun DependencyHandler.test() {
    testImplementation(Dependency.jUnit)
    androidTestImplementation(Dependency.jUnitAndroid)
    androidTestImplementation(Dependency.espresso)
}

fun DependencyHandler.viewModelKtx() {
    implementation(Dependency.viewModelKtx)
}

fun DependencyHandler.runtimeKtx() {
    implementation(Dependency.runtimeKtx)
}

fun DependencyHandler.coreKtx() {
    implementation(Dependency.coreKtx)
}

fun DependencyHandler.activityCompose() {
    implementation(Dependency.activityCompose)
}


