## Kotlin JSON Parser Benchmark

There has been a puzzle around me for a long time.

* `Why is everyone building JSON libraries?`
* `What is the performance gap between libraries`
* `What is the best JSON library for my project?`

My project use the kotlin language, and I want to benchmark the performance of the JSON libraries.
```
JMH version: 1.35
VM version: JDK 18.0.1.1, OpenJDK 64-Bit Server VM, 18.0.1.1+2-6
Kotlin version: 1.7.10
```

```
Benchmark                                 Mode     Cnt    Score      Error   Units
EishayTest.fastjson2                     thrpt       3  300.200 ± 2731.299  ops/ms
EishayTest.gson                          thrpt       3  250.525 ± 1985.290  ops/ms
EishayTest.jackson                       thrpt       3  137.790 ± 1226.736  ops/ms
EishayTest.moshi                         thrpt       3  225.760 ± 1340.233  ops/ms
EishayTest.origin                        thrpt       3   71.052 ±  469.031  ops/ms
```

It's very close to the benchmark of some libraries.

But...

* When the app receive the data from server and create object with the value.
* Why the libs is so faster than origin JSONObject operate?

I thought the result is not suitable the sense of the android platform.
I run the benchmark with the diff mode:

```
Benchmark                                 Mode     Cnt    Score      Error   Units
EishayTest.fastjson2                     thrpt       3  300.200 ± 2731.299  ops/ms
EishayTest.gson                          thrpt       3  250.525 ± 1985.290  ops/ms
EishayTest.jackson                       thrpt       3  137.790 ± 1226.736  ops/ms
EishayTest.moshi                         thrpt       3  225.760 ± 1340.233  ops/ms
EishayTest.origin                        thrpt       3   71.052 ±  469.031  ops/ms

EishayTest.fastjson2                      avgt       3    0.003 ±    0.032   ms/op
EishayTest.gson                           avgt       3    0.004 ±    0.031   ms/op
EishayTest.jackson                        avgt       3    0.008 ±    0.087   ms/op
EishayTest.moshi                          avgt       3    0.004 ±    0.035   ms/op
EishayTest.origin                         avgt       3    0.014 ±    0.093   ms/op

EishayTest.fastjson2                    sample   81254    0.008 ±    0.018   ms/op
EishayTest.fastjson2:fastjson2·p0.00    sample            0.002              ms/op
EishayTest.fastjson2:fastjson2·p0.50    sample            0.002              ms/op
EishayTest.fastjson2:fastjson2·p0.90    sample            0.003              ms/op
EishayTest.fastjson2:fastjson2·p0.95    sample            0.004              ms/op
EishayTest.fastjson2:fastjson2·p0.99    sample            0.022              ms/op
EishayTest.fastjson2:fastjson2·p0.999   sample            0.062              ms/op
EishayTest.fastjson2:fastjson2·p0.9999  sample            1.686              ms/op
EishayTest.fastjson2:fastjson2·p1.00    sample          446.693              ms/op

EishayTest.gson                         sample   62721    0.010 ±    0.020   ms/op
EishayTest.gson:gson·p0.00              sample            0.002              ms/op
EishayTest.gson:gson·p0.50              sample            0.003              ms/op
EishayTest.gson:gson·p0.90              sample            0.004              ms/op
EishayTest.gson:gson·p0.95              sample            0.006              ms/op
EishayTest.gson:gson·p0.99              sample            0.027              ms/op
EishayTest.gson:gson·p0.999             sample            0.086              ms/op
EishayTest.gson:gson·p0.9999            sample            0.392              ms/op
EishayTest.gson:gson·p1.00              sample          376.439              ms/op

EishayTest.jackson                      sample   69039    0.014 ±    0.025   ms/op
EishayTest.jackson:jackson·p0.00        sample            0.004              ms/op
EishayTest.jackson:jackson·p0.50        sample            0.004              ms/op
EishayTest.jackson:jackson·p0.90        sample            0.007              ms/op
EishayTest.jackson:jackson·p0.95        sample            0.009              ms/op
EishayTest.jackson:jackson·p0.99        sample            0.032              ms/op
EishayTest.jackson:jackson·p0.999       sample            0.123              ms/op
EishayTest.jackson:jackson·p0.9999      sample            1.620              ms/op
EishayTest.jackson:jackson·p1.00        sample          521.142              ms/op

EishayTest.moshi                        sample  102240    0.008 ±    0.012   ms/op
EishayTest.moshi:moshi·p0.00            sample            0.003              ms/op
EishayTest.moshi:moshi·p0.50            sample            0.003              ms/op
EishayTest.moshi:moshi·p0.90            sample            0.005              ms/op
EishayTest.moshi:moshi·p0.95            sample            0.007              ms/op
EishayTest.moshi:moshi·p0.99            sample            0.025              ms/op
EishayTest.moshi:moshi·p0.999           sample            0.076              ms/op
EishayTest.moshi:moshi·p0.9999          sample            0.456              ms/op
EishayTest.moshi:moshi·p1.00            sample          373.817              ms/op

EishayTest.origin                       sample   51993    0.025 ±    0.034   ms/op
EishayTest.origin:origin·p0.00          sample            0.009              ms/op
EishayTest.origin:origin·p0.50          sample            0.011              ms/op
EishayTest.origin:origin·p0.90          sample            0.021              ms/op
EishayTest.origin:origin·p0.95          sample            0.028              ms/op
EishayTest.origin:origin·p0.99          sample            0.051              ms/op
EishayTest.origin:origin·p0.999         sample            0.130              ms/op
EishayTest.origin:origin·p0.9999        sample            1.790              ms/op
EishayTest.origin:origin·p1.00          sample          544.211              ms/op

EishayTest.fastjson2                        ss       3  172.027 ± 5430.706   ms/op
EishayTest.gson                             ss       3  131.752 ± 4156.374   ms/op
EishayTest.jackson                          ss       3  163.715 ± 5163.480   ms/op
EishayTest.moshi                            ss       3  127.580 ± 4018.069   ms/op
EishayTest.origin                           ss       3  141.458 ± 4463.188   ms/op
```

`For one-short parsing, most libraries behave like your handwriting.`

You can fork the project and run the benchmark which you care.
