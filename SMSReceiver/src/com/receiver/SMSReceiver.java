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
    	modMap.put(0, "chattyrobin");
    	modMap.put(1, "chirp");
    	modMap.put(2, "cuckoo");
    	modMap.put(3, "doublesqwark");
    	modMap.put(4, "forestfloorbird");
    	modMap.put(5, "twitter");
    	modMap.put(6, "happytweeting");
    	modMap.put(7, "kerukeru");
    	modMap.put(8, "magpiesqwark");
    	modMap.put(9, "mockingbirdhappychatty");
    	modMap.put(10, "owlhootlong");
    	modMap.put(11, "owlhootshort");
    	modMap.put(12, "pigeoncoogrumpy");
    	modMap.put(13, "pissedoffcrow");
    	modMap.put(14, "pissedoffseagulls");

    	// create map to store

    	HashMap<Integer, String> customMap = new HashMap<Integer, String>();

    	// put values into map
    	customMap.put(33, "birds1"); // !
    	customMap.put(46, "verychirpybirdy"); // .
    	customMap.put(63, "twitter"); // ?
    	customMap.put(32, "twitter"); // " "
    	customMap.put(126, "screech"); // ~
    	customMap.put(48, "gobble"); // 0
    	customMap.put(49, "gobble"); // 1
    	customMap.put(50, "gobble"); // ...
    	customMap.put(51, "gobble"); // ...
    	customMap.put(52, "gobble"); // ...
    	customMap.put(53, "gobble"); // ...
    	customMap.put(54, "gobble"); // ...
    	customMap.put(55, "gobble"); // ...
    	customMap.put(56, "gobble"); // ...
    	customMap.put(57, "gobble"); // 9
    	
        
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
        	messageIntegers[n] = messageCharacters[n]%15;	
        }

        // handling audio files
        int resourceid = 0;
        MediaPlayer mediaplayer = null;
        try {
        	for (int n = 0; n < messageIntegers.length; n++)
        	{
        		resourceid = R.raw.class.getField((String)modMap.get(messageIntegers[n])).getInt(null);
				mediaplayer = MediaPlayer.create(context, resourceid);
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
        mediaplayer.release();
        
		Toast toast = Toast.makeText(context, "Array" + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
        toast.show();
    }
}
