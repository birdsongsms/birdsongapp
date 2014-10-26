/*
 Copyright 2008 Wissen Systems. All rights reserved.
 Author: Prashant Kalkar on 7:45:37 PM
 */
package com.receiver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        
    	//DICTIONARIES
    	// create map to store
    	//Map<String, List<String>> modMap = new HashMap<String, List<String>>();
    	HashMap<Integer, String> modMap = new HashMap<Integer, String>();
    	// put values into map
    	modMap.put(0, "birds1");
    	modMap.put(1, "");
    	modMap.put(2, "");
    	modMap.put(3, "");
    	modMap.put(4, "");
    	modMap.put(5, "");
    	modMap.put(6, "");
    	modMap.put(7, "");
    	modMap.put(8, "");
    	modMap.put(9, "");
    	modMap.put(10, "");
    	modMap.put(11, "");
    	modMap.put(12, "");
    	modMap.put(13, "");
    	modMap.put(14, "");

    	// create map to store

    	HashMap<Integer, String> customMap = new HashMap<Integer, String>();

    	// put values into map
    	customMap.put(33, "birds1"); // !
    	customMap.put(46, ""); // .
    	customMap.put(63, ""); // ?
    	customMap.put(32, ""); // " "
    	customMap.put(126, ""); // ~
    	customMap.put(48, ""); // 0
    	customMap.put(49, ""); // 1
    	customMap.put(50, ""); // ...
    	customMap.put(51, ""); // ...
    	customMap.put(52, ""); // ...
    	customMap.put(53, ""); // ...
    	customMap.put(54, ""); // ...
    	customMap.put(55, ""); // ...
    	customMap.put(56, ""); // ...
    	customMap.put(57, ""); // 9
    	
        
        // retrieves sms object and puts into string
    	Bundle bundle = intent.getExtras();
        Object messages[] = (Object[]) bundle.get("pdus");
        SmsMessage smsMessage[] = new SmsMessage[messages.length];
        for (int n = 0; n < messages.length; n++) {
            smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
        }
        String messageString = smsMessage[0].getMessageBody();
        // converting sms body string into utf-8 character array
        char[] messageCharacters = messageString.toCharArray();
        int[] messageIntegers= new int[messageCharacters.length];
        for (int n = 0; n < messageCharacters.length; n++) {
        	messageIntegers[n] = messageCharacters[n]%5;	
        }

        // handling audio files
        int resourceid = 0;
        try {
        	for (int n = 0; n < messageIntegers.length; n++)
        	{
        		resourceid = R.raw.class.getField((String)modMap.get(n)).getInt(null);
				MediaPlayer mediaplayer = MediaPlayer.create(context, resourceid);
				mediaplayer.start();
        	}
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
        
		Toast toast = Toast.makeText(context, "Array" + messageIntegers[0] + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
        toast.show();
    }
}
