package com.receiver;

import android.content.Context;
import android.media.MediaPlayer;

// takes SoundHandler(resourceid, n)
public class SoundRunnable implements Runnable{
	private int resourceid;
	private int n;
	private Context context;
	public SoundRunnable(int resourceid, int n, Context context){
		this.resourceid = resourceid;
		this.n = n;
		this.context = context;
	}
	public void run(){
		MediaPlayer mediaplayer = MediaPlayer.create(context, resourceid);
		mediaplayer.start();
		mediaplayer.release();
	}
}

/*	//put sound name into media array
		
}
//build media array
for (int n = 0; n < mediaplayer.length; n++)
{

//delay

Handler delaySound = new Handler();
delaySound.postDelayed(new Runnable() {
	
	@Override
	public void run() {
		//assemble the media playlist
		 
	}
} */