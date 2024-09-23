abstract class Foo : DefaultTask() {

    @get:OutputFile
    val output = project.objects.fileProperty()

    init {
        outputs.upToDateWhen { false }
    }

    @TaskAction
    protected fun run() {
        output.get().asFile.writeText(System.currentTimeMillis().toString())
    }
}

val foo by tasks.registering(Foo::class) {
    output = layout.buildDirectory.file("foo.txt")
}

val bar by tasks.registering {
    val fileProvider = foo.flatMap { it.output }
    inputs.property("x", fileProvider.map { it.asFile.readText() })
    doLast {
        println(inputs.properties)
    }
}

val baz by tasks.registering {
    val fileProperty = objects.fileProperty().value(foo.flatMap { it.output })
    inputs.property("x", fileProperty.map { it.asFile.readText() })
    doLast {
        println(inputs.properties)
    }
}
