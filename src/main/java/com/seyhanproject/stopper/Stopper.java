package com.seyhanproject.stopper;

import java.net.URL;
import java.net.HttpURLConnection;

public class Stopper {

	public static void main(String[] args) {
		boolean isAlive = call(args[0], "is_alive") == HttpURLConnection.HTTP_OK;
		if (isAlive) call(args[0], "stop");
	}

	private static int call(String port, String method) {
		HttpURLConnection httpClient = null;
		try {
			String urlAddress = "http://localhost:" + port + "/" + method;
			URL url = new URL(urlAddress);
			System.out.println("URL : " + urlAddress);
			httpClient = (HttpURLConnection) url.openConnection();
			httpClient.setUseCaches(false);
			httpClient.getInputStream();
			return httpClient.getResponseCode();
		} catch (Exception e) {
			return HttpURLConnection.HTTP_BAD_REQUEST;
		} finally {
			if (httpClient != null) {
				httpClient.disconnect();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) { }
		}
	}

}
