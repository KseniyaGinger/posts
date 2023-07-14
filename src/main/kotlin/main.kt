data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val created_by: Int?,
    val date: Int,
    val text: String,
    val reply_owner_id: Int,
    val reply_post_id: Int,
    val friends_only: Boolean,
    val post_type: String, //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val is_pinned: Boolean,
    val marked_as_ads: Boolean,
    val is_favorite: Boolean,
    val postponed_id: Int?,
    val comments: Comments,
    val copyright: Copyright,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postSource: PostSource,
    val geo: Geo,
    val donut: Donut,

    )

data class Comments(
    val count: Int = 10,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = false,
)

data class Copyright(
    val id: Int = 11,
    val link: String = "wow",
    var name: String = "Petya",
    var type: String = "friend"
)

data class Likes(
    val count: Int = 29,
    val user_likes: Boolean = false,
    val can_like: Boolean = true,
    val can_publish: Boolean = true
)

data class Reposts(
    val count: Int = 3,
    val user_reposted: Boolean = false
)

data class Views(
    val count: Int = 20
)

data class PostSource(
    val type: String = "vk", //тип источника. возможные значения: vk, widget, api, rss, sms
    val platform: String = "android", //название платформы: android, iphone, wphone
    val data: String = "profile activity"//тип действия. type - vk/widget. profile activity, profile photo (vk),
    // comments, like, poll (widget)
)

data class Geo(
    val type: String = "Sochi", //тип места
    val coordinates: String = "place"//координаты места;
    //val place: Object //описание места (если оно добавлено). Объект места.
)

data class Donut(
    val is_donut: Boolean = false, //запись доступна только платным подписчикам VK Donut
    val paid_duration: Int = 5, //время, в течение которого запись будет доступна только платным подписчикам VK Donut
    //val placeholder: Object
    val can_publish_free_copy: Boolean = true, //можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut
    val edit_mode: String = "all" //какие значения VK Donut можно изменить в записи.
// Возможные значения:all — всю информацию о VK Donut.
// duration — время, в течение которого запись будет доступна только платным подписчикам VK Donut.
)


fun main() {
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
    println(post)

    val comments = Comments()
    println(comments)
}

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()
    private var copyright = Copyright()
    private var likes = Likes()
    private var reposts = Reposts()
    private var views = Views()
    private var postSource = PostSource()
    private var geo = Geo()
    private var donut = Donut()
    private var lastId = 0

    fun createComment(postId: Int, comment: Comments): Comments {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment.copy()
                return comments.last()
            }
        }
        throw PostNotFoundException("Нет Поста с таким id: $postId")
    }

    fun clear() {
        posts = emptyArray()
        // также здесь нужно сбросить счетчик для id постов, если он у вас используется
        lastId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy()
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

    interface Attachment {
        var type: String
    }

    data class Photo(
        val id: Int = 23,
        val album_id: Int = 11,
        val ownerId: Int = 49
    )

    data class PhotoAttachment(val photo: Photo) : Attachment {
        override var type = "photo"
    }

    data class Audio(
        val id: Int = 89,
        val artist: String = "Adele",
        val title: String = "Skyfall"
    )

    data class AudioAttachment(val audio: Audio) : Attachment {
        override var type = "audio"
    }

    data class Document(
        val id: Int = 78,
        val title: String = "V.Pelevin - Batman Apollo",
        val ext: String = ".txt"
    )

    data class DocumentAttachment(val document: Document) : Attachment {
        override var type = "document"
    }

    data class Link(
        val url: String = "http...",
        val title: String = "News",
        val caption: String = "Don`t worry be happy"
    )

    data class LinkAttachment(val linkAttachment: LinkAttachment) : Attachment {
        override var type = "link"
    }

    data class Video(
        val title: String = "funny kitties",
        val description: String = "cute kitties having fun while you`re on work",
        val duration: Int = 50
    )

    data class VideoAttachment(val video: Video) : Attachment {
        override var type = "video"
    }
}

class PostNotFoundException(message: String) : RuntimeException (message) {

}
