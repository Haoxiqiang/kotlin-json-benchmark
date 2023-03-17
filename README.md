## Kotlin JSON Parser Benchmark

There has been a puzzle around me for a long time.

* `Why is everyone building JSON libraries?`
* `What is the performance gap between libraries`
* `What is the best JSON library for my project?`

My project use the kotlin language, and I want to benchmark the performance of the JSON libraries.
```
JMH version: 1.35
VM version: JDK 18.0.1.1, OpenJDK 64-Bit Server VM, 18.0.1.1+2-6
Kotlin version: 1.7.20
jackson version: 2.14.0
moshi version: 1.14.0
gson version: 2.10
fastjson2 version: 2.0.21
```

```
Benchmark                                 Mode     Cnt    Score      Error   Units
EishayTest.fastjson2                     thrpt      3  160.925 ± 2718.448  ops/ms
EishayTest.gson                          thrpt      3  166.018 ± 2053.943  ops/ms
EishayTest.jackson                       thrpt      3   72.280 ± 1111.439  ops/ms
EishayTest.moshi                         thrpt      3  137.261 ± 1723.508  ops/ms
EishayTest.origin                        thrpt      3   42.250 ±  467.543  ops/ms
```

It's very close to the benchmark of some libraries.

But...

* The app receive the data from server and create object with the value only once.
* Why the libs is so faster than origin JSONObject operate?

I thought the result is not suitable the sense of the android platform.
I run the benchmark with the diff mode:

```
enchmark                                 Mode    Cnt    Score      Error   Units
EishayTest.fastjson2                     thrpt      3  160.925 ± 2718.448  ops/ms
EishayTest.gson                          thrpt      3  166.018 ± 2053.943  ops/ms
EishayTest.jackson                       thrpt      3   72.280 ± 1111.439  ops/ms
EishayTest.moshi                         thrpt      3  137.261 ± 1723.508  ops/ms
EishayTest.origin                        thrpt      3   42.250 ±  467.543  ops/ms

EishayTest.fastjson2                      avgt      3    0.017 ±    0.416   ms/op
EishayTest.gson                           avgt      3    0.011 ±    0.220   ms/op
EishayTest.jackson                        avgt      3    0.112 ±    3.234   ms/op
EishayTest.moshi                          avgt      3    0.016 ±    0.327   ms/op
EishayTest.origin                         avgt      3    0.031 ±    0.479   ms/op

EishayTest.fastjson2                    sample  89589    0.014 ±    0.030   ms/op
EishayTest.fastjson2:fastjson2·p0.00    sample           0.003              ms/op
EishayTest.fastjson2:fastjson2·p0.50    sample           0.003              ms/op
EishayTest.fastjson2:fastjson2·p0.90    sample           0.008              ms/op
EishayTest.fastjson2:fastjson2·p0.95    sample           0.010              ms/op
EishayTest.fastjson2:fastjson2·p0.99    sample           0.020              ms/op
EishayTest.fastjson2:fastjson2·p0.999   sample           0.067              ms/op
EishayTest.fastjson2:fastjson2·p0.9999  sample           0.244              ms/op
EishayTest.fastjson2:fastjson2·p1.00    sample         825.229              ms/op
EishayTest.gson                         sample  69383    0.016 ±    0.029   ms/op
EishayTest.gson:gson·p0.00              sample           0.004              ms/op
EishayTest.gson:gson·p0.50              sample           0.004              ms/op
EishayTest.gson:gson·p0.90              sample           0.012              ms/op
EishayTest.gson:gson·p0.95              sample           0.016              ms/op
EishayTest.gson:gson·p0.99              sample           0.034              ms/op
EishayTest.gson:gson·p0.999             sample           0.132              ms/op
EishayTest.gson:gson·p0.9999            sample           2.192              ms/op
EishayTest.gson:gson·p1.00              sample         606.077              ms/op
EishayTest.jackson                      sample  60904    0.025 ±    0.041   ms/op
EishayTest.jackson:jackson·p0.00        sample           0.008              ms/op
EishayTest.jackson:jackson·p0.50        sample           0.008              ms/op
EishayTest.jackson:jackson·p0.90        sample           0.026              ms/op
EishayTest.jackson:jackson·p0.95        sample           0.034              ms/op
EishayTest.jackson:jackson·p0.99        sample           0.052              ms/op
EishayTest.jackson:jackson·p0.999       sample           0.171              ms/op
EishayTest.jackson:jackson·p0.9999      sample           0.925              ms/op
EishayTest.jackson:jackson·p1.00        sample         764.412              ms/op
EishayTest.moshi                        sample  57246    0.019 ±    0.035   ms/op
EishayTest.moshi:moshi·p0.00            sample           0.005              ms/op
EishayTest.moshi:moshi·p0.50            sample           0.005              ms/op
EishayTest.moshi:moshi·p0.90            sample           0.013              ms/op
EishayTest.moshi:moshi·p0.95            sample           0.025              ms/op
EishayTest.moshi:moshi·p0.99            sample           0.038              ms/op
EishayTest.moshi:moshi·p0.999           sample           0.115              ms/op
EishayTest.moshi:moshi·p0.9999          sample           1.975              ms/op
EishayTest.moshi:moshi·p1.00            sample         615.514              ms/op
EishayTest.origin                       sample  57079    0.031 ±    0.034   ms/op
EishayTest.origin:origin·p0.00          sample           0.016              ms/op
EishayTest.origin:origin·p0.50          sample           0.016              ms/op
EishayTest.origin:origin·p0.90          sample           0.030              ms/op
EishayTest.origin:origin·p0.95          sample           0.034              ms/op
EishayTest.origin:origin·p0.99          sample           0.047              ms/op
EishayTest.origin:origin·p0.999         sample           0.097              ms/op
EishayTest.origin:origin·p0.9999        sample           0.258              ms/op
EishayTest.origin:origin·p1.00          sample         585.105              ms/op

EishayTest.fastjson2                        ss      3  268.355 ± 8474.181   ms/op
EishayTest.gson                             ss      3  197.297 ± 6226.390   ms/op
EishayTest.jackson                          ss      3  256.122 ± 8078.626   ms/op
EishayTest.moshi                            ss      3  212.561 ± 6703.983   ms/op
EishayTest.origin                           ss      3  193.982 ± 6122.227   ms/op
```

`For one-short parsing, most libraries behave like your handwriting.`

My choice is that ordinary apps written in Java use the Gson, and those written in Kotlin use Moshi.

You can fork the project and run the benchmark which you care.
