package com.bruce.designer.util;

import android.content.Context;
import android.widget.Toast;


public class UiUtil {
	
	public static void showShortToast(Context context, String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	public static void showLongToast(Context context, String text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}
