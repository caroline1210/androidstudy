package com.ltd.mos.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class BaseDialog extends Dialog {
	
	public BaseDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public BaseDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

}
