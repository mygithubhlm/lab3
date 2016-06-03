package littlemylyn.tools;

import org.json.JSONArray;


// a tool class for data io, input json and output json
//

public interface ITransferer {
	// retrieve the json file at some path
	public JSONArray retrieve(String dir,String filename);
	
	
	// output the json object into some file at some path
	public  boolean dumpTo(JSONArray jobj,String path);
}
