package net.yakuraion.mangakko.core_repositories_impl.mappers

import net.yakuraion.mangakko.core_entity.Media
import net.yakuraion.mangakko.core_entity.Page
import net.yakuraion.mangakko.core_network.QueryPageMediaQuery

object PageMediaApiToPageMediaMapper {

    fun invoke(value: QueryPageMediaQuery.Page): Page<Media> {
        return Page(
            values = value.media()!!.map { MediaApiToMediaMapper.invoke(it) },
            totalCount = value.pageInfo()!!.total()!!,
            hasNextPage = value.pageInfo()!!.hasNextPage()!!
        )
    }
}
