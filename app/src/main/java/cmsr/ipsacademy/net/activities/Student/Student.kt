package cmsr.ipsacademy.net.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import cmsr.ipsacademy.net.R
import cmsr.ipsacademy.net.Util.SharedPreference
import cmsr.ipsacademy.net.activities.models.StudentInfo
import cmsr.ipsacademy.net.api.apiset
import cmsr.ipsacademy.net.api.controller
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Response


class Student : AppCompatActivity() {

    private var sharedPreference: SharedPreference? = null
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)

        sharedPreference = SharedPreference(this)

        getStudentDetails()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.frame_layout_container) as NavHostFragment
        navController = navHostFragment.navController


        val navigationView = findViewById<NavigationView>(R.id.navigation_menu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer)

        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

//        appBarConfiguration = AppBarConfiguration(navController,)

        navigationView.setupWithNavController(navController)



    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun getStudentDetails() {
        val computer_code = "52684"
        val userApi = controller.getInstance().create(apiset::class.java)


        userApi.getStudentDetails(computer_code)
            .enqueue(object : retrofit2.Callback<StudentInfo> {
                override fun onResponse(
                    call: Call<StudentInfo>,
                    response: Response<StudentInfo>
                ) {

                    if (response.body() != null){
                        Log.d("name", "Student name:-" + response.body()!!.student_info[0].student_name)
                    }

                }

                override fun onFailure(call: Call<StudentInfo>, t: Throwable) {
                    Log.d("error", t.toString())
                }
            })

    }


}