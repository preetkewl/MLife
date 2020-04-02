package com.mlife.utils;

import android.content.pm.PackageManager;

import static com.example.focpc.mahindralifespaces.utils.MlsApp.getContext;

public class Permissions {




    private boolean checkWriteExternalPermission()
    {
        String permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res = getContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

}
