##### Ganti IP API
di `com/ongghuen/diskoperindag/network/DiskoperindagApiService.kt`
```kotlin
// ...

const val BASE = "http://192.168.100.2:8000" // ini yang diganti, sesuaiin sama host backend
const val BASE_URL = "${BASE}/api/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
// ...
```

<hr/>

##### TODO
- [x] Sleep
- [ ] Notification each news are created
- [ ] Notification additional message from diskoperindag

<hr/>

#### References:
- Backend API: [here](https://github.com/Ongghuen/diskoperindag-web) 

#### Main Resources
- [Android Course](https://developer.android.com/courses/android-basics-kotlin/course)
- [Commonsware Book](https://commonsware.com/AndExplore/pages/index)

#### Other References/Resources
- [I'm trying to use safe args but when i add the dependencies to the project level build.gradle it gives me this error](https://stackoverflow.com/a/73208002/16032095)
- [New navigation component from arch with nested navigation graph](https://stackoverflow.com/a/51500083/16032095)
- [Navigate with BottomNavigationView inside fragment (not activity) using navigation component and navigation graph](https://stackoverflow.com/a/61529471/16032095)
