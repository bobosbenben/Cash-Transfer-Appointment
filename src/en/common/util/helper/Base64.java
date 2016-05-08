package en.common.util.helper;


public class Base64 {
	private final String keyStr = "ABCDEfghijKLMNOpqrstUVWXYz02abdceFGHIJklmnoPQRSTuvwxyZ13789465+/=";
	
	/**
	 * 解密
	 * @param input
	 * @return
	 */
	public String decode64(String input) {
		byte[] data = input.getBytes();
		String output = "";
		int chr1, chr2, chr3;
		int enc1, enc2, enc3, enc4;
		int i = 0;
		
		for (int j = 0; j < data.length; j++) {
			if (keyStr.indexOf(data[j]) < 0) {
				System.out
						.println("There were invalid base64 characters in the input text.\n"
								+ "Valid base64 characters are A-Z, a-z, 0-9, '+', '/', and '='\n"
								+ "Expect errors in decoding.");
				return "";
			}
		}
		do {
			enc1 = keyStr.indexOf(input.charAt(i++));
			enc2 = keyStr.indexOf(input.charAt(i++));
			enc3 = keyStr.indexOf(input.charAt(i++));
			enc4 = keyStr.indexOf(input.charAt(i++));

			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;

			output = output + (char) chr1;

			if (enc3 != 64) {
				output = output + (char) chr2;
			}
			if (enc4 != 64) {
				output = output + (char) chr3;
			}

			chr1 = chr2 = chr3 = 0;
			enc1 = enc2 = enc3 = enc4 = 0;

		} while (i < input.length());

		return Escape.unescape(output);
	}
	/**
	 * 加密
	 * @param input
	 * @return
	 */
	public String encode64(String input) {
		
		input = Escape.escape(input);
		String output = "";
		int chr1, chr2, chr3;
		int enc1, enc2, enc3, enc4;
		int i = 0;
		int len = input.length();
		do {

			chr1 = (int) input.charAt(i++);
			enc1 = chr1 >> 2;
			if (i < len) {
				chr2 = (int) input.charAt(i++);
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				if (!isNum(chr2)) {
					enc3 = enc4 = 64;
				}
				if (i < len) {
					chr3 = (int) input.charAt(i++);
					enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
					enc4 = chr3 & 63;

					if (!isNum(chr3)) {
						enc4 = 64;
					}
					output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
							+ keyStr.charAt(enc3) + keyStr.charAt(enc4);
				} else {
					
					enc4 = ((chr2 & 15) << 2);
					output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
							+ keyStr.charAt(enc4) + "=";
				}
			} else {
				
				enc4 = ((chr1 & 3) << 4);
				output = output + keyStr.charAt(enc1) + keyStr.charAt(enc4)
						+ "==";
			}

			chr1 = chr2 = chr3 = 0;
			enc1 = enc2 = enc3 = enc4 = 0;

		} while (i < len);
		return output;
	}

	public static boolean isNum(int str) {

		return String.valueOf(str).matches(
				"^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
