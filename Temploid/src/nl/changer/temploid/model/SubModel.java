
package nl.changer.temploid.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/****
 * Ideally this class should be superclass and {@link VideoDel} and others
 * should be the subclass. But looks like the programmer wrote their
 * fate to be like this. :/ 
 ****/
public class SubModel extends BaseModel implements Parcelable {

	public static final String TYPE_AUDIO = "audio";
	public static final String TYPE_VIDEO = "video";
	public static final String TYPE_IMAGE = "photo";
	
	private int mLength;
	
	private String mAwsRelativeUrl;
	private String mMimeType;
	private String mSignedUrl;

	private String mMediaType;
	protected String mMediaUrl;
	protected String mThumbnailUrl;

	public SubModel(String id, String type, String mediaUrl, String thumbnailUrl) {
		super(id);
		mMediaType = type;
		mMediaUrl = mediaUrl;
		mThumbnailUrl = thumbnailUrl;
	}
	
	public SubModel(String id, String type, String mediaUrl, String thumbnailUrl, int length) {
		super(id);
		mMediaType = type;
		mMediaUrl = mediaUrl;
		mThumbnailUrl = thumbnailUrl;
		mLength = length;
	}
	
	private SubModel(Parcel in) {
		super(in.readString());
        // This order must match the order in writeToParcel()
        // mId = in.readString();
        mMediaType = in.readString();
        mMediaUrl =  in.readString();
        mThumbnailUrl =  in.readString();
        // Continue doing this for the rest of your member data
    }
	
	public String getMediaType() {
		return this.mMediaType;
	}

	public void setMedia_type(String mediaType) {
		this.mMediaType = mediaType;
	}

	/***
	 * Url or the {@link Uri} of the media.
	 * High resolution image url if the media is an image.
	 ****/
	public String getUrl() {
		return this.mMediaUrl;
	}

	public void setMedia_url(String mediaUrl) {
		this.mMediaUrl = mediaUrl;
	}

	public String getThumbnailUrl() {
		return this.mThumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.mThumbnailUrl = thumbnailUrl;
	}
	
	public String getSignedUrl() {
		return mSignedUrl;
	}

	public void setSignedUrl(String signedUrl) {
		this.mSignedUrl = signedUrl;
	}

	public String getMimeType() {
		return mMimeType;
	}

	public void setMimeType(String mimeType) {
		this.mMimeType = mimeType;
	}

	public String getAwsRelativeUrl() {
		return mAwsRelativeUrl;
	}

	public void setAwsRelativeUrl(String awsRelativeUrl) {
		this.mAwsRelativeUrl = awsRelativeUrl;
	}
	
	public int getLength() {
		return mLength;
	}
	
	public static final ArrayList<SubModel> toList(JSONArray jsonArr) {
		if(jsonArr == null || jsonArr.length() == 0 ) {
			return null;	
		}
		
		ArrayList<SubModel> attachements = new ArrayList<SubModel>();
		
		for (int i = 0; i < jsonArr.length(); i++) {
			try {
				JSONObject obj = jsonArr.getJSONObject(i);
				SubModel media = toObject(obj);
				if(media != null)	attachements.add(media);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return attachements;
	}
	
	public static SubModel toObject(JSONObject object) {
		
		if(object == null || object.length() == 0) {
			return null;	
		}
		
		SubModel media = null;
		
		String id;
		String type = null;
		String mediaUrl = null;
		String thumbnailUrl = null;
		int length = 0;
		
		try {
			 id = object.has("id") ? object.getString("id") : null;
			 type = object.has("media_type") ? object.getString("media_type") : null;
			 mediaUrl = object.has("media_url") ? object.getString("media_url") : null;
			 thumbnailUrl = object.has("media_thumbnailUrl") ? object.getString("media_thumbnailUrl") : null;
			 media = new SubModel(id, type, mediaUrl, thumbnailUrl, length);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return media;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mId);
		dest.writeString(mMediaType);
		dest.writeString(mMediaUrl);
		dest.writeString(mThumbnailUrl);
	}
	
	// Just cut and paste this for now
    public static final Parcelable.Creator<SubModel> CREATOR = new Parcelable.Creator<SubModel>() {
        public SubModel createFromParcel(Parcel in) {
            return new SubModel(in);
        }

        public SubModel[] newArray(int size) {
            return new SubModel[size];
        }
    };
}

