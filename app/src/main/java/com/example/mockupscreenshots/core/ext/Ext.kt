package com.example.mockupscreenshots.core.ext

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.io.File
import java.io.FileOutputStream

fun NavGraphBuilder.composableWithArgs(
    route: String,
    vararg arguments: String,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments.map { navArgument(it) { defaultValue = "" } }
    ) { backStack ->
        content(backStack)
    }
}

fun NavBackStackEntry.getString(key: String) = arguments?.getString(key)
fun NavBackStackEntry.getInt(key: String) = arguments?.getString(key)?.toIntOrNull() ?: 0


@Composable
inline fun <reified VM : ViewModel> hiltActivityViewModel(): VM {
    val activity = LocalContext.current as ComponentActivity
    return hiltViewModel<VM>(activity)
}

fun View.capture(): Bitmap {
    return generateBitmap(this)
}

fun generateBitmap(view: View): Bitmap {
    val bitmap = Bitmap.createBitmap(
        view.width,
        view.height,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    view.layout(
        view.left,
        view.top,
        view.right,
        view.bottom
    )
    view.draw(canvas)
    return bitmap
}

fun Context.saveBitmap(bitmap: Bitmap) {
    val path = cacheDir.absolutePath
    val tempFile = File(path, "temp.png")
    val fOut = FileOutputStream(tempFile)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
    fOut.close()
}

fun Bitmap.saveScreenshot():String {
    val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    val fileName = "mockup_${System.currentTimeMillis()}.png"
    val tempFile = File(path, fileName)
    val fOut = FileOutputStream(tempFile)
    compress(Bitmap.CompressFormat.PNG, 100, fOut)
    fOut.close()
    return tempFile.absolutePath
}

fun Context.hasPermissions(permission: String) =
    run { ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED }