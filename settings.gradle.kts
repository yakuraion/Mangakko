rootProject.name = "Mangakko"

include(":app")
include(":modules:core-di")
include(":modules:core-utils")
include(":modules:core-testutils")
include(":modules:core-entity")
include(":modules:core-uikit")
include(":modules:core-feature")
include(":modules:core-repositories")
include(":modules:core-repositories-impl")
include(":modules:core-persistence")
include(":modules:core-persistence-impl")
include(":modules:core-network")
include(":modules:core-network-impl")

include(":modules:features:main")
include(":modules:features:main-impl")
include(":modules:features:pager")
include(":modules:features:pager-impl")
include(":modules:features:media")
include(":modules:features:media-impl")
include(":modules:features:favorites")
include(":modules:features:favorites-impl")
include(":modules:features:settings")
include(":modules:features:settings-impl")

exec { commandLine("git", "init") }
exec { commandLine("git", "config", "core.hooksPath", ".githooks") }
