import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {


    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val service = WallService
        val post = Post(
            0,
            1,
            2,
            3,
            "Test post",
            4,
            true,
            true,
            true,
            false,
            Comments()
        )

        val addedPost = service.add(post)
        assertNotEquals(0, addedPost.id)
    }

    @Test
    fun testUpdatePost() {
        val service = WallService
        val post = Post(
            0,
            1,
            2,
            3,
            "Test post",
            4,
            true,
            true,
            true,
            false,
            Comments()
        )
        service.add(post)

        val updated = service.update(post.copy(), 1)

        assertTrue(updated)
    }

    @Test
    fun testUpdateNonExistingPost() {
        val service = WallService
        val post = Post(
            0,
            1,
            2,
            3,
            "Test post",
            4,
            true,
            true,
            true,
            false,
            Comments()
        )

        val updated = service.update(post.copy(), 2)

        assertFalse(updated)
    }
}