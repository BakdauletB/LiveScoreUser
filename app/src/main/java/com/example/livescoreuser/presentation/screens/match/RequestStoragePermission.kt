package com.example.livescoresdu.presentation.screens.match

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestStoragePermission(onPermissionGranted: () -> Unit, onPermissionDenied: () -> Unit) {

    val scope = rememberCoroutineScope()

    // Camera permission state
    val permissionState = rememberPermissionState(
        android.Manifest.permission.READ_EXTERNAL_STORAGE, onPermissionResult = {
            if (it) onPermissionGranted() else onPermissionDenied()
        }
    )
    permissionState.status.shouldShowRationale


    when (permissionState.status) {
        // If the camera permission is granted, then show screen with the feature enabled
        PermissionStatus.Granted -> {
            onPermissionGranted()
        }
        is PermissionStatus.Denied -> {
            LaunchedEffect(key1 = scope) {
                permissionState.launchPermissionRequest()
            }
        }
    }
}
