package com.ltd.mos.image;

import android.view.View;

public class Compat {
	
	private static final int SIXTY_FPS_INTERVAL = 1000 / 60;
	
	public static void postOnAnimation(View view, Runnable runnable) {
//		if (VERSION.SDK_INT >= VERSION_CODES.BASE) {
//			SDK16.postOnAnimation(view, runnable);
//		} else {
			view.postDelayed(runnable, SIXTY_FPS_INTERVAL);
//		}
	}

}
