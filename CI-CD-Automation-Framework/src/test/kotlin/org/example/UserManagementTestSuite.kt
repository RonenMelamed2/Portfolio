package org.example

import okhttp3.OkHttpClient
import okhttp3.Request
import org.testng.Assert.assertNotNull
import org.testng.Assert.assertTrue
import org.testng.annotations.*

class UserManagementTestSuite {
    
    private lateinit var client: OkHttpClient
    private val baseUrl = "https://jsonplaceholder.typicode.com"
    
    @BeforeSuite
    fun beforeSuite() {
        println("")
        println("=========================================")
        println("UserManagementTestSuite: Starting Suite")
        println("=========================================")
        println("")
        client = OkHttpClient()
        println("  → Initialized HTTP client for User Management tests")
        println("")
    }
    
    @BeforeMethod
    fun beforeMethod() {
        println("")
        println("  ───────────────────────────────────────")
        println("  Starting new test method in UserManagementTestSuite")
        println("  ───────────────────────────────────────")
    }
    
    @AfterMethod
    fun afterMethod() {
        println("  ───────────────────────────────────────")
        println("  Completed test method in UserManagementTestSuite")
        println("  ───────────────────────────────────────")
        println("")
    }
    
    @AfterSuite
    fun afterSuite() {
        println("")
        println("=========================================")
        println("UserManagementTestSuite: Completing Suite")
        println("=========================================")
        println("")
    }
    
    @Test
    fun testGetAllUsers() {
        println("  Operation: Fetching all users from API")
        val url = "$baseUrl/users"
        val request = Request.Builder().url(url).build()
        
        try {
            client.newCall(request).execute().use { response ->
                assertTrue(response.isSuccessful, "Failed to get users. Status: ${response.code}")
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null")
                println("  ✓ Successfully retrieved ${responseBody?.length} characters of user data")
                println("  ✓ Response status: ${response.code}")
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to execute get all users request: ${e.message}", e)
        }
    }
    
    @Test
    fun testGetUserById() {
        println("  Operation: Fetching user by ID (ID: 1)")
        val userId = 1
        val url = "$baseUrl/users/$userId"
        val request = Request.Builder().url(url).build()
        
        try {
            client.newCall(request).execute().use { response ->
                assertTrue(response.isSuccessful, "Failed to get user $userId. Status: ${response.code}")
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null")
                println("  ✓ Successfully retrieved user $userId")
                println("  ✓ Response status: ${response.code}")
                // More flexible check - JSON might have spaces or be formatted differently
                assertTrue(responseBody!!.contains("\"id\"") && responseBody.contains("$userId"), 
                    "User ID not found in response")
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to execute get user by ID request: ${e.message}", e)
        }
    }
    
    @Test
    fun testGetUserPosts() {
        println("  Operation: Fetching posts for user (User ID: 1)")
        val userId = 1
        val url = "$baseUrl/users/$userId/posts"
        val request = Request.Builder().url(url).build()
        
        try {
            client.newCall(request).execute().use { response ->
                assertTrue(response.isSuccessful, "Failed to get posts for user $userId. Status: ${response.code}")
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null")
                println("  ✓ Successfully retrieved posts for user $userId")
                println("  ✓ Response status: ${response.code}")
                // More flexible check - JSON might have spaces
                assertTrue(responseBody!!.contains("\"userId\"") && responseBody.contains("$userId"), 
                    "User ID not found in posts")
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to execute get user posts request: ${e.message}", e)
        }
    }
    
    @Test
    fun testGetUserAlbums() {
        println("  Operation: Fetching albums for user (User ID: 1)")
        val userId = 1
        val url = "$baseUrl/users/$userId/albums"
        val request = Request.Builder().url(url).build()
        
        try {
            client.newCall(request).execute().use { response ->
                assertTrue(response.isSuccessful, "Failed to get albums for user $userId. Status: ${response.code}")
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null")
                println("  ✓ Successfully retrieved albums for user $userId")
                println("  ✓ Response status: ${response.code}")
                // More flexible check - JSON might have spaces
                assertTrue(responseBody!!.contains("\"userId\"") && responseBody.contains("$userId"), 
                    "User ID not found in albums")
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to execute get user albums request: ${e.message}", e)
        }
    }
}

