package littlemylyn.tools;

import org.json.JSONObject;

// a tool class for data io, input json and output json
//

public interface ITransferer {
	// retrieve the json file at some path
	public JSONObject retrieve(String path);
	
	
	// output the json object into some file at some path
	public  boolean dumpTo(JSONObject jobj,String path);
}
