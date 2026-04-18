import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marah.alsafadi.marah_alsafadi_final.screens.login.LoginScreen
import com.marah.alsafadi.marah_alsafadi_final.screens.main.MainContainer
import com.marah.alsafadi.marah_alsafadi_final.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash // هاد أول شي بيفتح
    ) {
        // 1. شاشة السبراش
        composable(route = Screen.Splash) {
            SplashScreen(navController = navController)
        }

        // 2. شاشة اللوجن
        composable(route = Screen.Login) {
            LoginScreen(navController = navController)
        }

        // 3. الحاوية الرئيسية (اللي فيها الـ Bottom Bar والشاشات التانية)
        composable(route = Screen.MainContainer) {
            MainContainer(navController = navController)
        }
    }
}