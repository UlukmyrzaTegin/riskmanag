package rts.util;

public class Translit {
	
	private static final String[] charTable = new String[65536];
	static {
		
		 charTable['�'] = "A";
	        charTable['�'] = "B";
	        charTable['�'] = "V";
	        charTable['�'] = "G";
	        charTable['�'] = "D";
	        charTable['�'] = "E";
	        charTable['�'] = "E";
	        charTable['�'] = "ZH";
	        charTable['�'] = "Z";
	        charTable['�'] = "I";
	        charTable['�'] = "I";
	        charTable['�'] = "K";
	        charTable['�'] = "L";
	        charTable['�'] = "M";
	        charTable['�'] = "N";
	        charTable['�'] = "O";
	        charTable['�'] = "P";
	        charTable['�'] = "R";
	        charTable['�'] = "S";
	        charTable['�'] = "T";
	        charTable['�'] = "U";
	        charTable['�'] = "F";
	        charTable['�'] = "H";
	        charTable['�'] = "C";
	        charTable['�'] = "CH";
	        charTable['�'] = "SH";
	        charTable['�'] = "SH";
	        charTable['�'] = "";
	        charTable['�'] = "Y";
	        charTable['�'] = "";
	        charTable['�'] = "E";
	        charTable['�'] = "U";
	        charTable['�'] = "YA";
	        
	        for (int i = 0; i < charTable.length; i ++) {
	        	char idx = (char) i;
	        	char lower = new String(new char[] {idx}).toLowerCase().charAt(0);
	        	if (charTable[i] != null) {
	        		charTable[lower] = charTable[i].toLowerCase();
	        		
	        	} 
	        }
	    }
	
	public static String getTranslitText(String text) {
		char charBuffer[] = text.toCharArray();
		StringBuffer sb = new StringBuffer(text.length());
		for (int i = 0; i < charBuffer.length; i++) {
			String replace = charTable[charBuffer[i]];
			if (replace == null) {
				sb.append(charBuffer[i]);
			} else {
				sb.append(replace);
			}
		}
		
		return sb.toString();		
	}
}
