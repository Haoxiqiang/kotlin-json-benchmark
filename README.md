## Kotlin JSON Parser Benchmark

There has been a puzzle around me for a long time.

* `Why is everyone building JSON libraries?`
* `What is the performance gap between libraries`
* `What is the best JSON library for my project?`

My project use the kotlin language, and I want to benchmark the performance of the JSON libraries.

```
Benchmark              Mode  Cnt    Score      Error   Units
EishayTest.fastjson2  thrpt    3  371.484 ± 2878.652  ops/ms
EishayTest.gson       thrpt    3  299.558 ± 2040.789  ops/ms
EishayTest.jackson    thrpt    3  148.649 ± 1498.229  ops/ms
EishayTest.moshi      thrpt    3  232.213 ± 1987.671  ops/ms
EishayTest.origin     thrpt    3   68.563 ±  498.598  ops/ms
```

It's very close to the benchmark of some libraries.

But...

When the app receive the data from server and create object with the value.
Why the libs is so faster than origin JSONObject operate?
I thought the result is not suitable the sense of the android platform.
I run the benchmark with the oneshot mode:

```
Benchmark             Mode  Cnt          Score            Error  Units
EishayTest.fastjson2    ss    3  171243753.000 ± 5408147918.880  ns/op
EishayTest.gson         ss    3  152200662.333 ± 4801101724.144  ns/op
EishayTest.jackson      ss    3  172943555.000 ± 5454871289.234  ns/op
EishayTest.moshi        ss    3  120802500.000 ± 3805115227.327  ns/op
EishayTest.origin       ss    3  114559912.667 ± 3612413251.738  ns/op
```

You can fork the project and run the benchmark which you care.
