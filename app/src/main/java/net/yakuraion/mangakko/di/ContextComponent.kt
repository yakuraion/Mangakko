package net.yakuraion.mangakko.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import net.yakuraion.mangakko.core_di.ContextProvider
import javax.inject.Singleton

@Singleton
@Component
interface ContextComponent : ContextProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ContextComponent
    }

    companion object {

        fun create(context: Context): ContextComponent {
            return DaggerContextComponent.builder()
                .context(context)
                .build()
        }
    }
}
