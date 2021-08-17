package net.yakuraion.mangakko.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.BindsInstance
import dagger.Component
import net.yakuraion.mangakko.core_di.ConvertersProvider
import javax.inject.Singleton

@Singleton
@Component
interface ConvertersComponent : ConvertersProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsGson(gson: Gson): Builder

        fun build(): ConvertersComponent
    }

    companion object {

        fun create(): ConvertersComponent {
            val gson = GsonBuilder()
                .create()
            return DaggerConvertersComponent.builder()
                .bindsGson(gson)
                .build()
        }
    }
}
