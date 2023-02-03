package com.example.mockupscreenshots.core.utils

import android.util.Log
import java.io.*

fun copyFile(inputFile: String, outputPath: String) {
    val `in`: InputStream?
    val out: OutputStream?
    try {
        val dir = File(outputPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        `in` = FileInputStream(inputFile)
        out = FileOutputStream(outputPath + File.separator + inputFile.split("/").last())
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
        `in`.close()
        out.flush()
        out.close()
    } catch (fileNotFoundException: FileNotFoundException) {
        Log.e("tag", fileNotFoundException.message.toString())
    } catch (e: Exception) {
        Log.e("tag", e.message.toString())
    }
}