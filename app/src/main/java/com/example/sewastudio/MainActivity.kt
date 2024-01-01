package com.example.sewastudio

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sewastudio.controller.AuthController
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sewastudio.ui.theme.SewaStudioTheme
import com.example.sewastudio.view.AuthPage
import com.example.sewastudio.view.AuthUI
import com.example.sewastudio.view.SplashPage
import com.example.sewastudio.view.karyawan.KaryawanHomePage
import com.example.sewastudio.view.pelanggan.BookingPage
import com.example.sewastudio.view.pelanggan.HomeUI
import com.example.sewastudio.view.pelanggan.PelangganHomePage
import com.example.sewastudio.view.pelanggan.Setting
import com.example.sewastudio.view.pelanggan.Setting2
import com.example.sewastudio.view.pemilik.CreateKaryawanPage
import com.example.sewastudio.view.pemilik.CreateStudioPage
import com.example.sewastudio.view.pemilik.PemilikHomePage
import com.example.sewastudio.view.pemilik.PemilikHomeUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val preferencesManager = PreferencesManager(context = LocalContext.current)
            val sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
            val navController = rememberNavController()
            var startDestination = "splash"
            var userID = sharedPreferences.getString("userID","")
            var username = sharedPreferences.getString("username", "")
            var password = sharedPreferences.getString("password", "")

            val loginComplete = remember { mutableStateOf(false) }
//            cek login secara otomatis apabila terdapat username dan password dari session sebelumnya.

            if (!loginComplete.value) {
                if (username != null && password != null){
                    AuthController.login(username, password, navController, preferencesManager) { success ->
                        if (success != null) {
                            loginComplete.value = true
                        }
                    }
                } else {
                    navController.navigate("auth-page")
                }
            }

            SewaStudioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
            NavHost(navController, startDestination = startDestination) {
                composable(route = "splash") {
                    SplashPage(navController)
                }
                composable(route = "auth-page") {
//                    AuthPage(navController)
                    AuthUI(navController)
                }
                composable(route = "pelangganhomepage") {
//                    PelangganHomePage(navController)
                    HomeUI(navController)
                }
                composable(route = "bookingpage") {
                    BookingPage(navController)
                }
                composable(route = "karyawanhomepage") {
                    KaryawanHomePage(navController)
                }
                composable(route = "pemilikhomepage") {
//                    PemilikHomePage(navController)
                    PemilikHomeUI(navController)
                }
                composable(route = "createstudiopage") {
                    CreateStudioPage(navController)
                }
                composable(route = "createkaryawanpage") {
                    CreateKaryawanPage(navController)
                }
                composable(route = "createorderpage") {
                    CreateStudioPage(navController)
                }
                composable(route = "setting") {
                    Setting(navController)
                }
                composable(route = "setting2") {
                    Setting2(navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Beranda", iconResId = Icons.Default.Menu, destination = "pelangganhomepage"
            ),
            BottomNavItem(
                label = "Orderan Kamu", iconResId = Icons.Default.DateRange, destination = "bookingpage"
            ),
            BottomNavItem(
                label = "Setting", iconResId = Icons.Default.Settings, destination = "setting"
            )
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.destination,
                onClick = {
                    navController.navigate(it.destination)
                },
                icon = {
                    Icon(it.iconResId, contentDescription = it.label, modifier = Modifier.width(20.dp), tint = Color(0xFF1F41BB))
                },
                label = { Text(text = it.label, color = Color(0xFF1F41BB)) },
            )
        }
    }
}

@Composable
fun BottomNavigationPemilik(navController: NavController) {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Studio", iconResId = Icons.Default.Home, destination = "pemilikhomepage"
            ),
            BottomNavItem(
                label = "Karyawan", iconResId = Icons.Default.Face, destination = "createkaryawanpage"
            ),
            BottomNavItem(
                label = "Setting", iconResId = Icons.Default.Settings, destination = "setting2"
            )
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.destination,
                onClick = {
                    navController.navigate(it.destination)
                },
                icon = {
                    Icon(it.iconResId, contentDescription = it.label, modifier = Modifier.width(20.dp), tint = Color(0xFF1F41BB))
                },
                label = { Text(text = it.label, color = Color(0xFF1F41BB)) },
            )
        }
    }
}

data class BottomNavItem(val label: String, val iconResId: ImageVector, val destination: String)
