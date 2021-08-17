package net.yakuraion.mangakko

import android.app.Application
import net.yakuraion.mangakko.core_di.app_provider.AppProvider
import net.yakuraion.mangakko.core_di.app_provider.AppProviderHolder
import net.yakuraion.mangakko.core_network_impl.di.NetworkComponent
import net.yakuraion.mangakko.core_persistence_impl.di.PersistenceComponent
import net.yakuraion.mangakko.core_repositories_impl.di.RepositoriesComponent
import net.yakuraion.mangakko.di.AppComponent
import net.yakuraion.mangakko.di.ConfigurationComponent
import net.yakuraion.mangakko.di.ContextComponent
import net.yakuraion.mangakko.di.ConvertersComponent
import net.yakuraion.mangakko.di.DispatchersComponent
import timber.log.Timber

class MangakkoApplication : Application(), AppProviderHolder {

    override lateinit var appProvider: AppProvider

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        setUpDI()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setUpDI() {
        val contextComponent = ContextComponent.create(this)
        val convertersComponent = ConvertersComponent.create()
        val persistenceComponent = PersistenceComponent.create(contextComponent, convertersComponent)
        val configurationComponent = ConfigurationComponent.create()
        val networkComponent = NetworkComponent.create(
            contextComponent,
            convertersComponent,
            configurationComponent
        )
        val dispatchersComponent = DispatchersComponent.create()
        val repositoriesComponent = RepositoriesComponent.create(networkComponent, persistenceComponent, configurationComponent)
        appProvider = AppComponent.create(repositoriesComponent, dispatchersComponent)
    }
}
