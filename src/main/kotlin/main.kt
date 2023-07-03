data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isFavorite: Boolean,
    val comments: Comments
)

data class Comments(
    val count: Int = 10,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = false,
)

fun main() {
    val post = Post(11,
        22,
        33,
        0,
        "hello",
        44,
        true,
        true,
        false,
        true,
        comments = Comments())
    println(post)

    val comments = Comments()
    println(comments)
}

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()
    private var lastId = 0

    fun clear() {
        posts = emptyArray()
        // также здесь нужно сбросить счетчик для id постов, если он у вас используется
        lastId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId)
        return posts.last()
    }

    fun update(post: Post, id: Int): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(id = post.id + 1)
                return true
            }
        }
        return false
    }
}