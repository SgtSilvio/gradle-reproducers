repositories {
    maven {
        url = uri(file("repository"))
        metadataSources {
            gradleMetadata()
        }
    }
}

val externalConf: Configuration by configurations.creating

dependencies {
    externalConf("org.example:test:1.0.0") {
        capabilities {
            requireCapability("org.example:test-variant1:1.0.0")
        }
    }
    externalConf("org.example:test:1.0.0") {
        capabilities {
            requireCapability("org.example:test-variant2:1.0.0")
        }
    }
}

val printExternalArtifactResults by tasks.registering {
    val artifactResults = externalConf.incoming.artifacts.resolvedArtifacts
    doLast {
        for (artifactResult in artifactResults.get()) {
            println("${artifactResult.variant} ${artifactResult.file.name}")
        }
    }
}



val variant1: Configuration by configurations.creating {
    attributes {
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named("artifact"))
    }
    outgoing.capability("org.example:test-variant1:1.0.0")
}
val variant2: Configuration by configurations.creating {
    attributes {
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named("artifact"))
    }
    outgoing.capability("org.example:test-variant2:1.0.0")
}

artifacts {
    add(variant1.name, file("repository/org/example/test/1.0.0/artifact.txt"))
    add(variant2.name, file("repository/org/example/test/1.0.0/artifact.txt"))
}

val projectConf: Configuration by configurations.creating

dependencies {
    projectConf(project(project.path)) {
        capabilities {
            requireCapability("org.example:test-variant1")
        }
    }
    projectConf(project(project.path)) {
        capabilities {
            requireCapability("org.example:test-variant2")
        }
    }
}

val printProjectArtifactResults by tasks.registering {
    val artifactResults = projectConf.incoming.artifacts.resolvedArtifacts
    doLast {
        for (artifactResult in artifactResults.get()) {
            println("${artifactResult.variant} ${artifactResult.file.name}")
        }
    }
}
