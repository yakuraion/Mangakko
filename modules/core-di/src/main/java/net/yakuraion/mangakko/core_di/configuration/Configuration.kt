package net.yakuraion.mangakko.core_di.configuration

import net.yakuraion.mangakko.core_di.BuildConfig

/**
 * Класс, предоставляющий доступ к конфигурации приложения.
 *
 * Содержит в себе данные, которые неизменны для целого приложения, но могут быть различны для разных сборок.
 * Поэтому, преимущественно, содержит данные из [BuildConfig] app модуля.
 */
class Configuration(val serverUrl: String)
