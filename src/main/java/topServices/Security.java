package topServices;

public class Security {
	public static void runGarbage() {
		System.gc();
	}

	public static String Decrypt(String key) {

		String keyPass = "seecnrsee420";
		Integer passLen = keyPass.length();
		Integer keyLen = key.length();

		String result = "";
		try {
			for (int i = 1; i < keyLen; i++) {

				Character character1 = key.charAt(i);
				Character character2 = keyPass.charAt(i / passLen);
				int char1 = character1;
				int char2 = character2;
				int character3 = (char2 - char1);

				result += Character.toString((char) character3);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;

	}

	public static String Encrypt(String key) {

		String keyPass = "seecnrsee420";
		Integer passLen = keyPass.length();
		Integer keyLen = key.length();

		String result = "";
		try {
			for (int i = 0; i < keyLen; i++) {

				Character character1 = key.charAt(i);
				Character character2 = keyPass.charAt(i % passLen);
				int char1 = character1;
				int char2 = character2;
				int character3 = (char1 - char2);

				result += Character.toString((char) character3);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;

	}

	public static String specialChars(String args) {
		args = args.replaceAll("[^a-zA-Z0-9]", "");
		return args;
	}
	
	public static String parseIp(String payload) {

		String chaIds = payload;
		String[] output = chaIds.split("/");
		String ipWithPort = output[1];
		output = ipWithPort.split(":");
		String ip = output[0];
		return ip;
	}

}
