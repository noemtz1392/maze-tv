object Dependencies {
    private val common = listOf(
        Dependency.Coroutines,
        Dependency.DateTime,
    )

    private val android = listOf(
        Dependency.CoreKtx,
    )

    private val androidTest = listOf(
        Dependency.AndroidTestJUnitExt,
    )

    private val commonTest = listOf(
        Dependency.JUnit,
        Dependency.MockK,
        Dependency.CoroutineTest,
    )

    val app = listOf(
        Dependency.Hilt,
        Dependency.Kapt.Hilt,
    )

    val data = listOf(
        Dependency.Coroutines,
        Dependency.Retrofit,
        Dependency.RetrofitConverterMoshi,
        Dependency.LoggingInterceptor,
        Dependency.Moshi,
        Dependency.MoshiAdapter,
        Dependency.MoshiKotlin,
        Dependency.Hilt,
        Dependency.Kapt.Hilt,
    )

    val domain = listOf(
        Dependency.Coroutines,
        Dependency.Dagger,
        Dependency.Kapt.Dagger,
    )

    val presentation = listOf(
        Dependency.AppCompat,
        Dependency.ViewModelKtx,
        Dependency.LegacySupport,
        Dependency.RecyclerView,
        Dependency.FragmentKtx,
        Dependency.NavigationRuntime,
        Dependency.NavigationFragment,
        Dependency.NavigationUi,
        Dependency.LifecycleRuntimeKtx,
        Dependency.LifecycleLiveData,
        Dependency.Material,
        Dependency.ConstraintLayout,
        Dependency.Hilt,
        Dependency.Kapt.Hilt,
    ).plus(common).plus(android)


}