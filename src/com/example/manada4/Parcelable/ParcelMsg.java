package com.example.manada4.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelMsg implements Parcelable{
	
	public ParcelMsg(){	
	}
	
	public void MsgParcelable(Parcel in,String[][] string_text) {
         //st_text = new string_text;
        readFromParcel(in);
    }

	private void readFromParcel(Parcel in) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		//dest.writeTypedArray(string_text, flags);
	}
}
