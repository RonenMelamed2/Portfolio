package org.example

import okhttp3.OkHttpClient
import okhttp3.Request
import org.testng.Assert.assertNotNull
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

class PoCUnitTest {

    @Test
    fun validate_api_status_and_content() {
        // Arrange
        val client = OkHttpClient()
        val url = "https://jsonplaceholder.typicode.com/todos/1" // A public, reliable test API endpoint

        val request = Request.Builder().url(url).build()

        println("Attempting to call API: $url")

        // Act & Assert
        try {
            // Execute the API call
            client.newCall(request).execute().use { response ->

                // Assertion 1: Check HTTP Status Code
                assertTrue(response.isSuccessful, "API call failed. Response code: ${response.code}")
                println("API call successful! Status: ${response.code}")

                // Assertion 2: Check Response Body is not empty (a minimal content check)
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null.")
                println("Response body received and is ${responseBody?.length} characters long.")

                // Optional: Check for a known string in the response
                assertTrue(responseBody!!.contains("delectus aut autem"), "Response content missing expected string.")
            }
        } catch (e: Exception) {
            // Assertion 3: Catch any network or IO error
            throw AssertionError("Failed to execute network request: ${e.message}", e)
        }
    }

}