package com.in28minutes.springboot.controller;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import org.springframework.security.crypto.codec.Base64;

import org.junit.Before;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();// new HttpHeaders();

	@Before
	public void before() {
		headers.add("Authorization", createHttpAuthenticationHeaderValue("user1", "secret1"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

//	@Test
//	public void testJsonAssert() throws JSONException {
//		JSONAssert.assertEquals("{id:1}", "{id:1;    name:Archit}", false);
//	}

	private String createHttpAuthenticationHeaderValue(String userId, String password) {
		// TODO Auto-generated method stub

		String auth = userId + ":" + password;

		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));

		String headerValue = "Basic " + new String(encodedAuth);

		return headerValue;
	}

	@Test
	public void retrieveSurveyQuestion() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		// Accept: application/json
		ResponseEntity<String> response = restTemplate.exchange(
				createUrlWithPort("/surveys/Survey1/questions/Question1"), HttpMethod.GET, entity, String.class);

		// String output = restTemplate.getForObject(url, String.class);
		// System.out.println("Response: " + response.getBody());

		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";

		try {
			JSONAssert.assertEquals(expected, response.getBody().toString(), false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void addQuestion() {

		// Accept: application/json

		Question question = new Question("DOESNOTMATTER", "Largest", "India",
				Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity<Question> entity = new HttpEntity<Question>(question, headers);

		ResponseEntity<String> response = restTemplate.exchange(createUrlWithPort("/surveys/Survey1/questions"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/surveys/Survey1/questions/"));
		System.out.println(actual);

	}

	private String createUrlWithPort(String retrieveAllQuestionsUrl) {
		return "http://localhost:" + port + retrieveAllQuestionsUrl;
	}

}
