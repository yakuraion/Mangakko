package net.yakuraion.mangakko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.yakuraion.mangakko.core_di.app_provider.AppProviderHolder
import net.yakuraion.mangakko.core_entity.settings.ThemeMode.DARK
import net.yakuraion.mangakko.core_entity.settings.ThemeMode.LIGHT
import net.yakuraion.mangakko.core_entity.settings.ThemeMode.SYSTEM
import net.yakuraion.mangakko.core_repositories.AppSettingsRepository
import net.yakuraion.mangakko.di.AppComponent
import net.yakuraion.mangakko.main.MainFeature
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var mainFeature: MainFeature

    @Inject
    lateinit var appSettingsRepository: AppSettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        observeThemeMode()
        setContentView(R.layout.activity_app)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (savedInstanceState == null) {
            setInitFragment(mainFeature.getMainFragment())
        }
    }

    private fun injectDependencies() {
        val appComponent = (application as AppProviderHolder).appProvider as AppComponent
        appComponent.inject(this)
    }

    private fun observeThemeMode() {
        lifecycleScope.launch {
            appSettingsRepository.themeModeFlow.collect { themeMode ->
                val flag = when (themeMode) {
                    SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                    DARK -> AppCompatDelegate.MODE_NIGHT_YES
                }
                AppCompatDelegate.setDefaultNightMode(flag)
            }
        }
    }

    private fun setInitFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
