/*
 Copyright 2008 Wissen Systems. All rights reserved.
 Author: Prashant Kalkar on 7:45:37 PM
 */
package com.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

/**
 * The class is called when SMS is received.
 * 
 * @author Prashant Kalkar
 * 
 * Create Date : Nov 23, 2008
 */
public class SMSReceiver extends BroadcastReceiver {

    /**
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        Object messages[] = (Object[]) bundle.get("pdus");
        SmsMessage smsMessage[] = new SmsMessage[messages.length];
        for (int n = 0; n < messages.length; n++) {
            smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            //smsIntegers[n] = Character.getNumericValue(smsMessage[n]);
        }
        String messageString = smsMessage[0].getMessageBody();
        char[] messageCharacters = messageString.toCharArray();
        int[] messageIntegers= new int[messageCharacters.length];
        for (int n = 0; n < messageCharacters.length; n++) {
        	messageIntegers[n] = messageCharacters[n]%5;	
        }
        for (int n = 0; n < messageCharacters.length; n++) {
        	//if me
        	String intString = messageIntegers.toString();
        }
        int resourceid = 0;
		try {
			resourceid = R.raw.class.getField("birds" + messageIntegers[0]).getInt(null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MediaPlayer mediaplayer = MediaPlayer.create(context, resourceid);
		mediaplayer.start();
		mediaplayer = MediaPlayer.create(context, R.raw.birds1);
		//mediaplayer.start();
		mediaplayer = MediaPlayer.create(context, R.raw.birds2);
		//mediaplayer.start();
        
		Toast toast = Toast.makeText(context, "Array" + messageIntegers[0] + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
        toast.show();
    }
}
