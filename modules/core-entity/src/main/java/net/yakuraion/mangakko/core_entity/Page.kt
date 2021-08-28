package net.yakuraion.mangakko.core_entity

class Page<T>(
    val values: List<T>,
    val totalCount: Int,
    val hasNextPage: Boolean
)
