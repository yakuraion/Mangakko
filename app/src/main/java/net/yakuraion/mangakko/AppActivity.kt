package net.yakuraion.mangakko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import net.yakuraion.mangakko.core_di.app_provider.AppProviderHolder
import net.yakuraion.mangakko.di.AppComponent
import net.yakuraion.mangakko.main.MainFeature
import javax.inject.Inject

class AppActivity : AppCompatActivity() {

    @Inject
    lateinit var mainFeature: MainFeature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(R.layout.activity_app)
        if (savedInstanceState == null) {
            setInitFragment(mainFeature.getMainFragment())
        }
    }

    private fun injectDependencies() {
        val appComponent = (application as AppProviderHolder).appProvider as AppComponent
        appComponent.inject(this)
    }

    private fun setInitFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
