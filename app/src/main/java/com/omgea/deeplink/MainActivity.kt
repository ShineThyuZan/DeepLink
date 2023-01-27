package com.omgea.deeplink

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.omgea.deeplink.ui.theme.DeepLinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeepLinkTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable(route = "home") {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = { navController.navigate("detail") }) {
                                Text(text = "To detail screen")
                            }
                        }
                    }
                    composable(
                        route = "detail",
                        deepLinks = listOf(
                            navDeepLink {
                                uriPattern = "https://youkn/{id}"
                                action = Intent.ACTION_VIEW
                            }
                        ),
                        arguments = listOf(
                            navArgument("id"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){ entry ->
                        val id = entry.arguments?.getInt("id")
                        Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                        ){
                            Text(text = "This id is $id")
                        }
                    }
                }
            }
        }
    }
}
