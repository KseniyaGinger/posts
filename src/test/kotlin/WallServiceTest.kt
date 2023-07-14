import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WallServiceTest {


    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val service = WallService
        val post = Post(
            11,
            22,
            33,
            0,
            0,
            "hello",
            12,
            45,
            false,
            "true",
            63,
            true,
            true,
            true,
            false,
            false,
            true,
            56,
            comments = Comments(),
            copyright = Copyright(),
            likes = Likes(),
            reposts = Reposts(),
            views = Views(),
            postSource = PostSource(),
            geo = Geo(),
            donut = Donut(),
        )

        val addedPost = service.add(post)
        assertNotEquals(0, addedPost.id)
    }

    @Test
    fun testUpdatePost() {
        val service = WallService
        val post = Post(
            11,
            22,
            33,
            0,
            0,
            "hello",
            12,
            45,
            false,
            "true",
            63,
            true,
            true,
            true,
            false,
            false,
            true,
            56,
            comments = Comments(),
            copyright = Copyright(),
            likes = Likes(),
            reposts = Reposts(),
            views = Views(),
            postSource = PostSource(),
            geo = Geo(),
            donut = Donut(),
        )
        service.add(post)

        val updated = service.update(post.copy(), 1)

        assertTrue(updated)
    }

    @Test
    fun testUpdateNonExistingPost() {
        val service = WallService
        val post = Post(
            11,
            22,
            33,
            0,
            0,
            "hello",
            12,
            45,
            false,
            "true",
            63,
            true,
            true,
            true,
            false,
            false,
            true,
            56,
            comments = Comments(),
            copyright = Copyright(),
            likes = Likes(),
            reposts = Reposts(),
            views = Views(),
            postSource = PostSource(),
            geo = Geo(),
            donut = Donut(),
        )

        val updated = service.update(post.copy(), 2)

        assertFalse(updated)
    }

    @Test
    fun testCreateCommentWithExistingPostId() {
        val post = Post(
            11,
            22,
            33,
            0,
            0,
            "hello",
            12,
            45,
            false,
            "true",
            63,
            true,
            true,
            true,
            false,
            false,
            true,
            56,
            comments = Comments(),
            copyright = Copyright(),
            likes = Likes(),
            reposts = Reposts(),
            views = Views(),
            postSource = PostSource(),
            geo = Geo(),
            donut = Donut(),
        )
        WallService.add(post)

        val comment = Comments(10, true, groupsCanPost = true, canClose = false, canOpen = false)
        val createdComment = WallService.createComment(1, comment)

        assertEquals(comment, createdComment)
    }

    @Test(expected = PostNotFoundException::class)
    fun testCreateCommentWithNonExistingPostId() {
        val comment = Comments(10, true, groupsCanPost = true, canClose = false, canOpen = false)
        WallService.createComment(1, comment)
    }
}