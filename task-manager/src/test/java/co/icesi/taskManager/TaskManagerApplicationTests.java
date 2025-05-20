package co.icesi.taskManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskManagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TestRestTemplate restTemplate;

	private String authToken;

	@Test
	void testLoginEndpoint_shouldAuthenticateUser() {
		String loginJson = """
				{
					"username": "jdoe",
					"password": "password"
				}
				""";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(loginJson, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/login", request, String.class);

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(response.getBody()).isNotEqualTo("Invalid password");
		assertThat(response.getBody()).isNotEqualTo("User not found");
	}

	@BeforeEach
	void obtainAuthToken() {
		String loginJson = """
				{
				    "username": "jdoe",
				    "password": "password"
				}
				""";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(loginJson, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/login", request, String.class);
		this.authToken = response.getBody();
	}

	@Test
	void testLoginEndpoint_shouldHaveValidToken() {
		assertThat(authToken).isNotNull();
		assertThat(authToken).isNotEqualTo("Invalid password");
		assertThat(authToken).isNotEqualTo("User not found");
	}

	private HttpHeaders getAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (authToken != null) {
			headers.set("Authorization", "Bearer " + authToken);
		}
		return headers;
	}

	/*
	 * ------------------------------------- REAL TESTS
	 * ------------------------------------------
	 */

	@Test
	void testCreateTask() {
		Map<String, Object> task = new HashMap<>();
		task.put("name", "Test Task");
		task.put("description", "Test Description");
		task.put("notes", "Some notes");
		task.put("priority", 1L);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(task, getAuthHeaders());
		ResponseEntity<Map> response = restTemplate.postForEntity("/tasks", request, Map.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().get("id")).isNotNull();
		assertThat(response.getBody().get("name")).isEqualTo("Test Task");
	}

	@Test
	void testUpdateTask() {
		Map<String, Object> task = new HashMap<>();
		task.put("name", "Update Task");
		task.put("description", "Update Description");
		task.put("priority", 3L);

		HttpEntity<Map<String, Object>> createRequest = new HttpEntity<>(task, getAuthHeaders());
		ResponseEntity<Map> createResponse = restTemplate.postForEntity("/tasks", createRequest, Map.class);

		Long taskId = Long.valueOf(createResponse.getBody().get("id").toString());

		task.put("name", "Updated Task Name");
		task.put("id", taskId);
		HttpEntity<Map<String, Object>> updateRequest = new HttpEntity<>(task, getAuthHeaders());
		ResponseEntity<Map> updateResponse = restTemplate.exchange("/tasks", HttpMethod.PUT, updateRequest, Map.class);

		assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(updateResponse.getBody()).isNotNull();
		assertThat(updateResponse.getBody().get("name")).isEqualTo("Updated Task Name");
	}

	@Test
	void testDeleteTask() {
		Map<String, Object> task = new HashMap<>();
		task.put("name", "Delete Task");
		task.put("description", "Delete Description");
		task.put("priority", 4L);

		HttpEntity<Map<String, Object>> createRequest = new HttpEntity<>(task, getAuthHeaders());
		ResponseEntity<Map> createResponse = restTemplate.postForEntity("/tasks", createRequest, Map.class);

		Long taskId = Long.valueOf(createResponse.getBody().get("id").toString());

		HttpEntity<Void> deleteRequest = new HttpEntity<>(getAuthHeaders());
		ResponseEntity<Void> deleteResponse = restTemplate.exchange("/tasks/" + taskId, HttpMethod.DELETE,
				deleteRequest, Void.class);

		assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		ResponseEntity<Map> getResponse = restTemplate.exchange("/tasks/" + taskId, HttpMethod.GET, deleteRequest,
				Map.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void testGetAllTasks() {
		HttpEntity<Void> request = new HttpEntity<>(getAuthHeaders());
		ResponseEntity<Map[]> response = restTemplate.exchange("/tasks", HttpMethod.GET, request, Map[].class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}

	@Test
	void testCreateTask_WithUser() {

		String loginJson = """
				{
				    "username": "asmith",
				    "password": "password"
				}
				""";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(loginJson, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/login", request, String.class);
		String authTokenAlice = response.getBody();

		System.out.println(authTokenAlice);
		
		HttpHeaders headersRequest = new HttpHeaders();
		headersRequest.setContentType(MediaType.APPLICATION_JSON);
		if (authTokenAlice != null) {
			headersRequest.set("Authorization", "Bearer " + authTokenAlice);
		}

		Map<String, Object> task = new HashMap<>();
		task.put("name", "Test Task");
		task.put("description", "Test Description");
		task.put("notes", "Some notes");
		task.put("priority", 1L);

		HttpEntity<Map<String, Object>> requestCreateTask = new HttpEntity<>(task, headersRequest);
		ResponseEntity<Map> responseCreateTask = restTemplate.postForEntity("/tasks", requestCreateTask, Map.class);

		assertThat(responseCreateTask.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

}
