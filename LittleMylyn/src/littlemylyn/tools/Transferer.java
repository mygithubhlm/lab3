package littlemylyn.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONTokener;



public class Transferer implements ITransferer {
	@Override
	public JSONArray retrieve(String dir,String filename) {
				File file = new File(dir+"/"+filename);
				if(!file.exists()){
					file=new File(dir);
					if(!file.exists()) file.mkdirs();
					try {
						file=new File(file,filename);
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Error when open file");
					}
				}
				
				
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
					System.out.println("Error file not found");
				} catch (IOException e) {
					System.out.println("Error with IO");
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							System.out.println("Error with IO");
						}
					}
				}
				
				if(data.toString().trim().length()==0) return new JSONArray();
				
				
				JSONTokener jsonTokener = new JSONTokener(data.toString());
				JSONArray retJSONObject;
				retJSONObject = (JSONArray) jsonTokener.nextValue();
				return retJSONObject;
	}

	@Override
	public boolean dumpTo(JSONArray jobj, String path) {
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
