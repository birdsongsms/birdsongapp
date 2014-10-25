package org.anddev.android.smsexample;
 
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentReceiver;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
 
public class SMSReceiver extends IntentReceiver {
        /** TAG used for Debug-Logging */
        private static final String LOG_TAG = "SMSReceiver";
 
        /** The Action fired by the Android-System when a SMS was received.
         * We are using the Default Package-Visibility */
        private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
        // @Override
        public void onReceiveIntent(Context context, Intent intent) {
                if (intent.getAction().equals(ACTION)) {
                        // if(message starts with SMStretcher recognize BYTE)
                        StringBuilder sb = new StringBuilder();
                       
                        /* The SMS-Messages are 'hiding' within the extras of the Intent. */
                        Bundle bundle = intent.getExtras();
                        if (bundle != null) {
                                /* Get all messages contained in the Intent*/
                                SmsMessage[] messages =
                                        Telephony.Sms.Intents.getMessagesFromIntent(intent);
                               
                                /* Feed the StringBuilder with all Messages found. */
                                for (SmsMessage currentMessage : messages){
                                        sb.append("Received compressed SMSnFrom: ");
                                        /* Sender-Number */
                                        sb.append(currentMessage.getDisplayOriginatingAddress());
                                        sb.append("n----Message----n");
                                        /* Actual Message-Content */
                                        sb.append(currentMessage.getDisplayMessageBody());
                                }
                        }
                        /* Logger Debug-Output */
                        Log.i(LOG_TAG, "[SMSApp] onReceiveIntent: " + sb);
 
                        /* Show the Notification containing the Message. */
                        Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
                        /* Start the Main-Activity */
                        Intent i = new Intent(context, SMSActivity.class);
                        i.setLaunchFlags(Intent.NEW_TASK_LAUNCH);
                        context.startActivity(i);
                }
        }
}