package littlemylyn.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Transferer implements ITransferer {

	@Override
	public JSONObject retrieve(String path) {
				File file = new File(path);
				BufferedReader reader = null;
				StringBuffer data = new StringBuffer();
				//
				try {
					reader = new BufferedReader(new FileReader(file));
					String temp = null;
					while ((temp = reader.readLine()) != null) {
						data.append(temp);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				JSONTokener jsonTokener = new JSONTokener(data.toString());
				JSONObject retJSONObject;
				retJSONObject = (JSONObject) jsonTokener.nextValue();
				return retJSONObject;
	}

	@Override
	public boolean dumpTo(JSONObject jobj, String path) {
		// TODO Auto-generated method stub
	    FileWriter fw;
		try {
			fw = new FileWriter(path);
		    PrintWriter out = new PrintWriter(fw);
		    out.write(jobj.toString());
		    out.println();
		    out.close();
		    fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error When Dumpping");
			return false;
		}
		
		return true;
	}

}
