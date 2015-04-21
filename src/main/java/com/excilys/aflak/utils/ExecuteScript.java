package com.excilys.aflak.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteScript {

	public static void execute() {

		// --user=admincdb
		try {
			String line;
			Process p = Runtime.getRuntime().exec(
					"./src/test/resources/refresh-db-test.sh");
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}

	}

}
