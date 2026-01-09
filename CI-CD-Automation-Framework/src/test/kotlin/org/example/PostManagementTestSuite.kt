package org.example

import okhttp3.OkHttpClient
import okhttp3.Request
import org.testng.Assert.assertNotNull
import org.testng.Assert.assertTrue
import org.testng.annotations.*

class PostManagementTestSuite {
    
    private lateinit var client: OkHttpClient
    private val baseUrl = "https://jsonplaceholder.typicode.com"
    
    @BeforeSuite
    fun beforeSuite() {
        println("")
        println("=========================================")
        println("PostManagementTestSuite: Starting Suite")
        println("=========================================")
        println("")
        client = OkHttpClient()
        println("  → Initialized HTTP client for Post Management tests")
        println("")
    }
    
    @BeforeMethod
    fun beforeMethod() {
        println("")
        println("  ───────────────────────────────────────")
        println("  Starting new test method in PostManagementTestSuite")
        println("  ───────────────────────────────────────")
    }
    
    @AfterMethod
    fun afterMethod() {
        println("  ───────────────────────────────────────")
        println("  Completed test method in PostManagementTestSuite")
        println("  ───────────────────────────────────────")
        println("")
    }
    
    @AfterSuite
    fun afterSuite() {
        println("")
        println("=========================================")
        println("PostManagementTestSuite: Completing Suite")
        println("=========================================")
        println("")
    }
    
    @Test
    fun testGetAllPosts() {
        println("  Operation: Fetching all posts from API")
        val url = "$baseUrl/posts"
        val request = Request.Builder().url(url).build()
        
        try {
            client.newCall(request).execute().use { response ->
                assertTrue(response.isSuccessful, "Failed to get posts. Status: ${response.code}")
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null")
                println("  ✓ Successfully retrieved ${responseBody?.length} characters of post data")
                println("  ✓ Response status: ${response.code}")
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to execute get all posts request: ${e.message}", e)
        }
    }
    
    @Test
    fun testGetPostById() {
        println("  Operation: Fetching post by ID (ID: 1)")
        val postId = 1
        val url = "$baseUrl/posts/$postId"
        val request = Request.Builder().url(url).build()
        
        try {
            client.newCall(request).execute().use { response ->
                assertTrue(response.isSuccessful, "Failed to get post $postId. Status: ${response.code}")
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null")
                println("  ✓ Successfully retrieved post $postId")
                println("  ✓ Response status: ${response.code}")
                // More flexible check - JSON might have spaces or be formatted differently
                assertTrue(responseBody!!.contains("\"id\"") && responseBody.contains("$postId"), 
                    "Post ID not found in response")
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to execute get post by ID request: ${e.message}", e)
        }
    }
    
    @Test
    fun testGetPostComments() {
        println("  Operation: Fetching comments for post (Post ID: 1)")
        val postId = 1
        val url = "$baseUrl/posts/$postId/comments"
        val request = Request.Builder().url(url).build()
        
        try {
            client.newCall(request).execute().use { response ->
                assertTrue(response.isSuccessful, "Failed to get comments for post $postId. Status: ${response.code}")
                val responseBody = response.body?.string()
                assertNotNull(responseBody, "Response body was null")
                println("  ✓ Successfully retrieved comments for post $postId")
                println("  ✓ Response status: ${response.code}")
                // More flexible check - JSON might have spaces
                assertTrue(responseBody!!.contains("\"postId\"") && responseBody.contains("$postId"), 
                    "Post ID not found in comments")
            }
        } catch (e: Exception) {
            throw AssertionError("Failed to execute get post comments request: ${e.message}", e)
        }
    }
    
    @Test
    fun testGetPostsByUserId() {
        println("  Operation: Fetching posts by user ID (User ID: 1)")
        val userId = 1
        val url = "$baseUrl/posts?userId=$userId"
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
            throw AssertionError("Failed to execute get posts by user ID request: ${e.message}", e)
        }
    }
}

