package com.bridgelabz.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import javax.security.sasl.AuthorizeCallback;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

@Service
public class SheetsAndJava 
{
	private static Sheets sheetServices;
	private String APPLICATIONS_NAME = "Google Application Example";
		
	private static Credential auhorise() throws Exception
	{
		InputStream in = SheetsAndJava.class.getResourceAsStream("/credentials .json");
		
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				
				JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
				
		);
		
		List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
		
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
               GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(), clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()) 
				.authorize("user");
		return credential;
	}
	
	public Sheets getSheetsService() throws Exception
	{
		Credential credential = auhorise();
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName(APPLICATIONS_NAME)
				.build();
	}
	
	
}
