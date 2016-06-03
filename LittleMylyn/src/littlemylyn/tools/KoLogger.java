package littlemylyn.tools;

// a logger for debug
public class KoLogger {
	public static void logErr(boolean funcOut,String hint){
		if(!funcOut){
			System.out.println("Failure: "+hint);
		}
	}
}
