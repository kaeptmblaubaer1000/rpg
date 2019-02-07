apply<IdePlugin>()

val rpgVersion = File(projectDir, "version").readText(Charsets.UTF_8)
allprojects {
    version = rpgVersion
}
