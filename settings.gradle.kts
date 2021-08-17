rootProject.name = "Mangakko"

include(":app")
include(":modules:core-di")
include(":modules:core-entity")
include(":modules:core-ui")
include(":modules:core-feature")
include(":modules:core-repositories")
include(":modules:core-repositories-impl")
include(":modules:core-persistence")
include(":modules:core-persistence-impl")
include(":modules:core-network")
include(":modules:core-network-impl")

include(":modules:features:main")
include(":modules:features:main-impl")

exec { commandLine("git", "init") }
exec { commandLine("git", "config", "core.hooksPath", ".githooks") }
