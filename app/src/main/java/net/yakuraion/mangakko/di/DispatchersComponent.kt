package net.yakuraion.mangakko.di

import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import net.yakuraion.mangakko.core_di.dispatchers.Dispatchers
import net.yakuraion.mangakko.core_di.dispatchers.DispatchersProvider
import javax.inject.Singleton

@Singleton
@Component
interface DispatchersComponent : DispatchersProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsDispatchers(dispatchers: Dispatchers): Builder

        fun build(): DispatchersComponent
    }

    companion object {

        fun create(): DispatchersComponent {
            val dispatchers = getDefaultDispatchers()
            return DaggerDispatchersComponent.builder()
                .bindsDispatchers(dispatchers)
                .build()
        }

        private fun getDefaultDispatchers(): Dispatchers {
            return object : Dispatchers {
                override val io: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
                override val main: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
                override val compute: CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
            }
        }
    }
}
