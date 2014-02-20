package com.kagami.kanadroid.format;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
	private String mRoman;
	private String mHirakana;
	private String mKatakana;

	public Item(String roman, String hira, String kata) {
		mRoman = roman;
		mHirakana = hira;
		mKatakana = kata;
	}

	public String getRoman() {
		return mRoman;
	}

	public void setRoman(String roman) {
		this.mRoman = roman;
	}

	public String getHirakana() {
		return mHirakana;
	}

	public void setHirakana(String hirakana) {
		this.mHirakana = hirakana;
	}

	public String getKatakana() {
		return mKatakana;
	}

	public void setKatakana(String katakana) {
		this.mKatakana = katakana;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mRoman + "-" + mHirakana + "-" + mKatakana;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mRoman);
		dest.writeString(mHirakana);
		dest.writeString(mKatakana);

	}

	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
		public Item createFromParcel(Parcel in) {
			return new Item(in);
		}

		public Item[] newArray(int size) {
			return new Item[size];
		}
	};

	private Item(Parcel in) {
		mRoman = in.readString();
		mHirakana=in.readString();
		mKatakana=in.readString();
	}

}
