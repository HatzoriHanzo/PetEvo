package com.example.petevopierre.Activities.Util;

import android.app.Activity;

import com.example.petevopierre.R;
import com.google.zxing.integration.android.IntentIntegrator;


public class Util {

    public static void startQrCodeScan(Activity activity) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt(activity.getString(R.string.mensagem_ler_qrcode));
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();

}
}
