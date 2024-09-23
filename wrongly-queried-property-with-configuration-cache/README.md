# Reproducer for [Gradle Issue xxxxx](https://github.com/gradle/gradle/issues/xxxxx)

## Working Task: `bar`

```bash
rm -r .gradle/configuration-cache build/foo.txt || true && \
./gradlew bar --configuration-cache --console=plain
```
Output:
```
Calculating task graph as no cached configuration is available for tasks: bar
> Task :foo

> Task :bar
{x=1727120271800}

BUILD SUCCESSFUL in 374ms
2 actionable tasks: 2 executed
Configuration cache entry stored.
```

## Failing Task: `baz`

```bash
rm -r .gradle/configuration-cache build/foo.txt || true && \
./gradlew baz --configuration-cache --console plain
```
Output:
```
Calculating task graph as no cached configuration is available for tasks: baz

FAILURE: Build failed with an exception.

* What went wrong:
/Users/sgiebl/Projects/sgtsilvio/gradle-reproducers/wrongly-queried-property-with-configuration-cache/build/foo.txt (No such file or directory)
> /Users/sgiebl/Projects/sgtsilvio/gradle-reproducers/wrongly-queried-property-with-configuration-cache/build/foo.txt (No such file or directory)

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

BUILD FAILED in 357ms
Configuration cache entry stored.
```
