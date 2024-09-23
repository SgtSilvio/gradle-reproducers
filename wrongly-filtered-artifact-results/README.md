# Reproducer for [Gradle Issue 29977](https://github.com/gradle/gradle/issues/29977)

ResolvedArtifactResults of different variants for the same file are wrongly deduplicated

```bash
./gradlew printExternalArtifactResults printProjectArtifactResults
 ```
Output:
```
> Task :printExternalArtifactResults
org.example:test:1.0.0 variant variant1 artifact.txt

> Task :printProjectArtifactResults
configuration ':variant1' artifact.txt
configuration ':variant2' artifact.txt
```
Expected output:
```
> Task :printExternalArtifactResults
org.example:test:1.0.0 variant variant1 artifact.txt
org.example:test:1.0.0 variant variant2 artifact.txt

> Task :printProjectArtifactResults
configuration ':variant1' artifact.txt
configuration ':variant2' artifact.txt
```
