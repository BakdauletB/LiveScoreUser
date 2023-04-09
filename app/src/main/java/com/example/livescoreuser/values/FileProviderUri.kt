package com.example.livescoresdu.uilibrary.values

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.UUID

const val FILE_PROVIDER_URI = "ffinbank.MyFreedom.provider"


fun File.uriFromFile(context: Context) = run {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        FileProvider.getUriForFile(context, FILE_PROVIDER_URI, this)
    } else {
        Uri.fromFile(this)
    }
}

fun File.fromUri(context: Context, uri: Uri): File {
    val destinationFilename =
        File(context.filesDir.path + File.separatorChar + queryName(context, uri))
    try {
        context.contentResolver.openInputStream(uri).use { ins ->
            if (ins != null) {
                createFileFromStream(
                    ins,
                    destinationFilename
                )
            }
        }
    } catch (ex: java.lang.Exception) {
        Log.e("Save File", "${ex.message}")
        ex.printStackTrace()
    }
    return destinationFilename
}

fun createFileFromStream(ins: InputStream, destination: File?) {
    try {
        FileOutputStream(destination).use { os ->
            val buffer = ByteArray(4096)
            var length: Int
            while (ins.read(buffer).also { length = it } > 0) {
                os.write(buffer, 0, length)
            }
            os.flush()
        }
    } catch (ex: Exception) {
        Log.e("Save File", "${ex.message}")
        ex.printStackTrace()
    }
}

private fun queryName(context: Context, uri: Uri): String {
    val returnCursor = context.contentResolver.query(uri, null, null, null, null)
    val nameIndex = returnCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor?.moveToFirst()
    val name = returnCursor?.getString(nameIndex ?: 0)
    returnCursor?.close()
    return name ?: "default-file-${UUID.randomUUID().timestamp()}"
}