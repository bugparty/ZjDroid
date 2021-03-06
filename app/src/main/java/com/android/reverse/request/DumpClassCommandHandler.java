package com.android.reverse.request;

import com.android.reverse.collecter.DexFileInfoCollecter;
import com.android.reverse.util.Logger;

public class DumpClassCommandHandler implements CommandHandler {

	private String mCookie;

	public DumpClassCommandHandler(String mCookie) {
		this.mCookie = mCookie;
	}

	@Override
	public void doAction() {
		String[] loadClass = DexFileInfoCollecter.getInstance().dumpLoadableClass(mCookie);
		if (loadClass != null) {
			Logger.log("Start Loadable ClassName ->");
			String className = null;
			for (int i = 0; i < loadClass.length; i++) {
				className = loadClass[i];
				if (!this.isFilterClass(className)) {
					Logger.log("ClassName = " + className);
				}
			}
			Logger.log("End Loadable ClassName");
		}else{
			Logger.log("Can't find class loaded by the dex");
		}
	}

	private final String[] filterClassName = { "android.support.v4.", "com.android.reverse.", "org.jf.", "org.keplerproject." };

	private boolean isFilterClass(String className) {
		String filterName = null;
		for (int i = 0; i < filterClassName.length; i++) {
			filterName = filterClassName[i];
			if (className.startsWith(filterName)) {
				return true;
			}
		}
		return false;
	}

}
