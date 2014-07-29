package nl.changer.temploid.model;

import org.json.JSONException;
import org.json.JSONObject;


/***
 * Common fields of all the models go into this class.
 ****/
public class BaseModel {

	protected String mId;
	
	public BaseModel(String id) {
		mId = id;
	}
	
	public String getId() {
		return this.mId;
	}
   	
	public void setId(String id) {
		this.mId = id;
	}

	public static BaseModel toObject(JSONObject obj) {
		
		if(obj == null || obj.length() == 0 )
			return null;
		
		String id = null;
		
		try {
			id = obj.has("id") ? obj.getString("id") : null;
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new BaseModel(id);
	}
}
