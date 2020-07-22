package com.news.newsapi
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.news.newsapi.api.NewsService
import com.news.newsapi.databinding.ActivityMainBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var service: NewsService
    override fun supportFragmentInjector() = dispatchingAndroidInjector
    lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.tab_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        // Set up ActionBar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.sourcesTab -> {
                    supportActionBar!!.show()
                    supportActionBar?.title = "Sources"
                }
                R.id.newsTab -> {
                    supportActionBar!!.show()
                    supportActionBar?.title = "Latest News"
                }
                R.id.newsViewFragment -> {
                    supportActionBar!!.show()
                    supportActionBar?.title = "News View"
                }
            }
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        navigation_view.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.SourcesMenu-> {
                    navController.navigate(R.id.sourcesTab)
                    drawerLayout.closeDrawers()
                }
                R.id.exitMenu-> {
                    doubleBackToExitPressedOnce = true
                    applicationExit()
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener  true
        }
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayUseLogoEnabled(true)


    }

    fun setSideMenuIcon()
    {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if ( navController.currentDestination?.id == R.id.newsTab ) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                }
                doubleBackToExitPressedOnce = true
                Toast.makeText(this, getString(R.string.please_click_back_again_to_exit), Toast.LENGTH_SHORT).show()
                Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
            }
            else{
                super.onBackPressed()
            }
        }
    }

    private fun applicationExit()
    {
        if (doubleBackToExitPressedOnce) {

            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(a)
            finish()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, resources.getString(R.string.please_click_back_again_to_exit), Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
