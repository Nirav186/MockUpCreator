package com.mobileappxperts.mockupgenerator.mockupmaker.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import net.lingala.zip4j.ZipFile
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

fun Context.saveAndShareZip(screenshots: List<String>, zipName: String) {
    val destPath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath +
                File.separator + zipName.plus(".zip")
    val listOfFiles = mutableListOf<File>()
    screenshots.forEach {
        listOfFiles.add(File(it))
    }
    if (File(destPath).exists()) {
        File(destPath).delete()
    }
    ZipFile(destPath).addFiles(listOfFiles)
    shareZip(File(destPath))
}

fun Context.shareZip(file: File) {
    val uri: Uri = FileProvider.getUriForFile(
        this,
        packageName.plus(".provider"),
        file
    )
    val share = Intent()
    share.action = Intent.ACTION_SEND
    share.type = "application/*"
    share.putExtra(Intent.EXTRA_STREAM, uri)
    startActivity(share)
}