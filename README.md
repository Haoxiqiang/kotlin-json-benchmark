## Kotlin JSON Parser Benchmark

There has been a puzzle around me for a long time.

* `Why is everyone building JSON libraries?`
* `What is the performance gap between libraries`
* `What is the best JSON library for my project?`

My project use the kotlin language, and I want to benchmark the performance of the JSON libraries.
```
JMH version: 1.35
VM version: JDK 18.0.1.1, OpenJDK 64-Bit Server VM, 18.0.1.1+2-6
```

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

* When the app receive the data from server and create object with the value.
* Why the libs is so faster than origin JSONObject operate?

I thought the result is not suitable the sense of the android platform.
I run the benchmark with the diff mode:

```
Benchmark                                 Mode      Cnt    Score    Error   Units

EishayTest.fastjson2                     thrpt        5  454.106 ± 65.038  ops/ms
EishayTest.gson                          thrpt        5  341.500 ± 41.634  ops/ms
EishayTest.jackson                       thrpt        5  187.227 ± 26.863  ops/ms
EishayTest.moshi                         thrpt        5  286.422 ± 43.145  ops/ms
EishayTest.origin                        thrpt        5   94.493 ± 20.757  ops/ms

EishayTest.fastjson2                      avgt        5    0.002 ±  0.001   ms/op
EishayTest.gson                           avgt        5    0.003 ±  0.001   ms/op
EishayTest.jackson                        avgt        5    0.005 ±  0.001   ms/op
EishayTest.moshi                          avgt        5    0.003 ±  0.001   ms/op
EishayTest.origin                         avgt        5    0.011 ±  0.001   ms/op

EishayTest.fastjson2                    sample  1418814    0.002 ±  0.001   ms/op
EishayTest.fastjson2:fastjson2·p0.00    sample             0.002            ms/op
EishayTest.fastjson2:fastjson2·p0.50    sample             0.002            ms/op
EishayTest.fastjson2:fastjson2·p0.90    sample             0.002            ms/op
EishayTest.fastjson2:fastjson2·p0.95    sample             0.002            ms/op
EishayTest.fastjson2:fastjson2·p0.99    sample             0.004            ms/op
EishayTest.fastjson2:fastjson2·p0.999   sample             0.031            ms/op
EishayTest.fastjson2:fastjson2·p0.9999  sample             0.087            ms/op
EishayTest.fastjson2:fastjson2·p1.00    sample             1.686            ms/op

EishayTest.gson                         sample  1061867    0.003 ±  0.001   ms/op
EishayTest.gson:gson·p0.00              sample             0.002            ms/op
EishayTest.gson:gson·p0.50              sample             0.003            ms/op
EishayTest.gson:gson·p0.90              sample             0.003            ms/op
EishayTest.gson:gson·p0.95              sample             0.003            ms/op
EishayTest.gson:gson·p0.99              sample             0.017            ms/op
EishayTest.gson:gson·p0.999             sample             0.037            ms/op
EishayTest.gson:gson·p0.9999            sample             0.103            ms/op
EishayTest.gson:gson·p1.00              sample             1.724            ms/op

EishayTest.jackson                      sample  1212352    0.005 ±  0.001   ms/op
EishayTest.jackson:jackson·p0.00        sample             0.004            ms/op
EishayTest.jackson:jackson·p0.50        sample             0.005            ms/op
EishayTest.jackson:jackson·p0.90        sample             0.005            ms/op
EishayTest.jackson:jackson·p0.95        sample             0.005            ms/op
EishayTest.jackson:jackson·p0.99        sample             0.023            ms/op
EishayTest.jackson:jackson·p0.999       sample             0.046            ms/op
EishayTest.jackson:jackson·p0.9999      sample             0.563            ms/op
EishayTest.jackson:jackson·p1.00        sample             1.532            ms/op

EishayTest.moshi                        sample  1154083    0.003 ±  0.001   ms/op
EishayTest.moshi:moshi·p0.00            sample             0.003            ms/op
EishayTest.moshi:moshi·p0.50            sample             0.003            ms/op
EishayTest.moshi:moshi·p0.90            sample             0.003            ms/op
EishayTest.moshi:moshi·p0.95            sample             0.004            ms/op
EishayTest.moshi:moshi·p0.99            sample             0.008            ms/op
EishayTest.moshi:moshi·p0.999           sample             0.036            ms/op
EishayTest.moshi:moshi·p0.9999          sample             1.259            ms/op
EishayTest.moshi:moshi·p1.00            sample             1.890            ms/op

EishayTest.origin                       sample  1159981    0.011 ±  0.001   ms/op
EishayTest.origin:origin·p0.00          sample             0.009            ms/op
EishayTest.origin:origin·p0.50          sample             0.010            ms/op
EishayTest.origin:origin·p0.90          sample             0.011            ms/op
EishayTest.origin:origin·p0.95          sample             0.012            ms/op
EishayTest.origin:origin·p0.99          sample             0.033            ms/op
EishayTest.origin:origin·p0.999         sample             0.068            ms/op
EishayTest.origin:origin·p0.9999        sample             0.396            ms/op
EishayTest.origin:origin·p1.00          sample             1.595            ms/op

EishayTest.fastjson2                        ss           484.791            ms/op
EishayTest.gson                             ss           401.647            ms/op
EishayTest.jackson                          ss           544.319            ms/op
EishayTest.moshi                            ss           410.080            ms/op
EishayTest.origin                           ss           378.352            ms/op
```

`For one-short parsing, most libraries behave like your handwriting.`

You can fork the project and run the benchmark which you care.
