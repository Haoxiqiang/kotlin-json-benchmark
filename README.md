## Kotlin JSON Parser Benchmark

There has been a puzzle around me for a long time.

* `Why is everyone building JSON libraries?`
* `What is the performance gap between libraries`
* `What is the best JSON library for my project?`

My project use the kotlin language, and I want to benchmark the performance of the JSON libraries.
```
# JMH version: 1.37
# VM version: JDK 17.0.13, OpenJDK 64-Bit Server VM, 17.0.13+0
# VM invoker: /opt/homebrew/Cellar/openjdk@17/17.0.13/libexec/openjdk.jdk/Contents/Home/bin/java
# VM options: -javaagent:/Users/haoxiqiang/Applications/IntelliJ IDEA Community Edition.app/Contents/lib/idea_rt.jar=53634:/Users/haoxiqiang/Applications/IntelliJ IDEA Community Edition.app/Contents/bin -Dfile.encoding=UTF-8
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
Kotlin version: 1.9.25
jackson version: 2.18.2
moshi version: 1.15.2
gson version: 2.10
fastjson2 version: 2.0.21
dsl-json version: 2.0.2
```

```
Benchmark                                 Mode     Cnt    Score      Error   Units
EishayTest.dslJson             thrpt      3   	11.936 ±   31.418  ops/ms
EishayTest.fastjson2           thrpt      3  	664.035 ± 2378.379  ops/ms
EishayTest.gson                thrpt      3  	656.478 ± 1244.248  ops/ms
EishayTest.jackson             thrpt      3  	415.791 ± 1939.918  ops/ms
EishayTest.moshi               thrpt      3  	444.553 ±  804.125  ops/ms
EishayTest.origin              thrpt      3  	153.647 ±  235.919  ops/ms
```

It's very close to the benchmark of some libraries.

But...

* The app receive the data from server and create object with the value only once.
* Why the libs is so faster than origin JSONObject operate?

I thought the result is not suitable the sense of the android platform.
I run the benchmark with the diff mode:

```
Benchmark                       Mode    Cnt    Score      Error   Units
EishayTest.dslJson             thrpt      3   11.936 ±   31.418  ops/ms
EishayTest.fastjson2           thrpt      3  664.035 ± 2378.379  ops/ms
EishayTest.gson                thrpt      3  656.478 ± 1244.248  ops/ms
EishayTest.jackson             thrpt      3  415.791 ± 1939.918  ops/ms
EishayTest.moshi               thrpt      3  444.553 ±  804.125  ops/ms
EishayTest.origin              thrpt      3  153.647 ±  235.919  ops/ms

EishayTest.dslJson              avgt      3    0.082 ±    0.207   ms/op
EishayTest.fastjson2            avgt      3    0.002 ±    0.008   ms/op
EishayTest.gson                 avgt      3    0.002 ±    0.003   ms/op
EishayTest.jackson              avgt      3    0.003 ±    0.013   ms/op
EishayTest.moshi                avgt      3    0.002 ±    0.004   ms/op
EishayTest.origin               avgt      3    0.007 ±    0.013   ms/op

EishayTest.dslJson            sample  36503    0.082 ±    0.010   ms/op
EishayTest.dslJson:p0.00      sample           0.066              ms/op
EishayTest.dslJson:p0.50      sample           0.073              ms/op
EishayTest.dslJson:p0.90      sample           0.085              ms/op
EishayTest.dslJson:p0.95      sample           0.093              ms/op
EishayTest.dslJson:p0.99      sample           0.136              ms/op
EishayTest.dslJson:p0.999     sample           0.432              ms/op
EishayTest.dslJson:p0.9999    sample           9.131              ms/op
EishayTest.dslJson:p1.00      sample         104.333              ms/op
EishayTest.fastjson2          sample  76701    0.005 ±    0.012   ms/op
EishayTest.fastjson2:p0.00    sample           0.001              ms/op
EishayTest.fastjson2:p0.50    sample           0.001              ms/op
EishayTest.fastjson2:p0.90    sample           0.002              ms/op
EishayTest.fastjson2:p0.95    sample           0.002              ms/op
EishayTest.fastjson2:p0.99    sample           0.005              ms/op
EishayTest.fastjson2:p0.999   sample           0.034              ms/op
EishayTest.fastjson2:p0.9999  sample           0.336              ms/op
EishayTest.fastjson2:p1.00    sample         288.883              ms/op
EishayTest.gson               sample  81036    0.003 ±    0.004   ms/op
EishayTest.gson:p0.00         sample           0.001              ms/op
EishayTest.gson:p0.50         sample           0.001              ms/op
EishayTest.gson:p0.90         sample           0.002              ms/op
EishayTest.gson:p0.95         sample           0.002              ms/op
EishayTest.gson:p0.99         sample           0.004              ms/op
EishayTest.gson:p0.999        sample           0.040              ms/op
EishayTest.gson:p0.9999       sample           0.280              ms/op
EishayTest.gson:p1.00         sample          92.799              ms/op
EishayTest.jackson            sample  92451    0.006 ±    0.010   ms/op
EishayTest.jackson:p0.00      sample           0.002              ms/op
EishayTest.jackson:p0.50      sample           0.002              ms/op
EishayTest.jackson:p0.90      sample           0.003              ms/op
EishayTest.jackson:p0.95      sample           0.003              ms/op
EishayTest.jackson:p0.99      sample           0.009              ms/op
EishayTest.jackson:p0.999     sample           0.091              ms/op
EishayTest.jackson:p0.9999    sample           0.547              ms/op
EishayTest.jackson:p1.00      sample         287.834              ms/op
EishayTest.moshi              sample  81468    0.004 ±    0.005   ms/op
EishayTest.moshi:p0.00        sample           0.002              ms/op
EishayTest.moshi:p0.50        sample           0.002              ms/op
EishayTest.moshi:p0.90        sample           0.003              ms/op
EishayTest.moshi:p0.95        sample           0.003              ms/op
EishayTest.moshi:p0.99        sample           0.007              ms/op
EishayTest.moshi:p0.999       sample           0.058              ms/op
EishayTest.moshi:p0.9999      sample           0.650              ms/op
EishayTest.moshi:p1.00        sample         112.067              ms/op
EishayTest.origin             sample  72276    0.008 ±    0.004   ms/op
EishayTest.origin:p0.00       sample           0.006              ms/op
EishayTest.origin:p0.50       sample           0.006              ms/op
EishayTest.origin:p0.90       sample           0.008              ms/op
EishayTest.origin:p0.95       sample           0.008              ms/op
EishayTest.origin:p0.99       sample           0.012              ms/op
EishayTest.origin:p0.999      sample           0.044              ms/op
EishayTest.origin:p0.9999     sample           1.020              ms/op
EishayTest.origin:p1.00       sample          94.372              ms/op

EishayTest.dslJson                ss      3   35.692 ± 1115.557   ms/op
EishayTest.fastjson2              ss      3   91.235 ± 2880.543   ms/op
EishayTest.gson                   ss      3   31.186 ±  982.993   ms/op
EishayTest.jackson                ss      3   93.007 ± 2932.430   ms/op
EishayTest.moshi                  ss      3   37.329 ± 1175.207   ms/op
EishayTest.origin                 ss      3   28.856 ±  908.861   ms/op
```

`For one-short parsing, most libraries behave like your handwriting.`

My choice is that ordinary apps written in Java use the Gson, and those written in Kotlin use Moshi.

You can fork the project and run the benchmark which you care.
