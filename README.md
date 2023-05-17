##### Ganti IP API
di `lib/presentation/utils/default.dart`
```kotlin
...

const val BASE = "http://192.168.100.2:8000" // ini yang diganti, sesuaiin sama host backend
const val BASE_URL = "${BASE}/api/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
...
```

<hr/>

##### TODO
- [x] Sleep
- [ ] Notification each news are created
- [ ] Notification additional message from diskoperindag

<hr/>

Referensi:
- Backend API: [here](https://github.com/Ongghuen/diskoperindag-web) 
