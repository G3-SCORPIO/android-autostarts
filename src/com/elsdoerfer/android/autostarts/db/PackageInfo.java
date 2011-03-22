package com.elsdoerfer.android.autostarts.db;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * This can be compared to android.content.pm.PackageInfo (et al),
 * except that we are forced to parse the package manifest files
 * ourselves, so we don't use the classes in android.content.pm.
 */
public class PackageInfo implements Parcelable {
	public String packageName;
	public String packageLabel;
	public Drawable icon;
	public boolean isSystem;

	public PackageInfo(android.content.pm.PackageInfo packageInfo) {
		this.packageName = packageInfo.packageName;
	}

	/**
	 * Return a label identifying the package.
	 */
	public String getLabel() {
		if (packageLabel != null && !packageLabel.equals(""))
			return packageLabel;
		else
			return packageName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(packageName);
		dest.writeString(packageLabel);
		dest.writeInt(isSystem ? 1 : 0);
		// TODO: deal with icon!
	}

	public static final Parcelable.Creator<PackageInfo> CREATOR
	= new Parcelable.Creator<PackageInfo>()
	{
		public PackageInfo createFromParcel(Parcel in) {
			return new PackageInfo(in);
		}

		public PackageInfo[] newArray(int size) {
			return new PackageInfo[size];
		}
	};

	private PackageInfo(Parcel in) {
		packageName = in.readString();
		packageLabel = in.readString();
		isSystem = in.readInt() == 1;
	}
}