package com.example.luminary.data.repository

import com.example.luminary.data.model.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor() {

    private val sampleBody = """
        The pace of change in our world has never been faster, and the implications ripple through every corner of human experience. From the way we communicate to how we make decisions, each technological shift carries with it a cascade of second and third-order effects that even the most prescient observers struggle to fully anticipate.

        What makes this moment distinct from previous technological inflection points is the simultaneity of change across multiple domains. We are not simply witnessing advances in computing power, or in materials science, or in our understanding of biology — we are seeing all of these domains advance in parallel, and increasingly, in conversation with one another.

        The convergence of artificial intelligence with the physical sciences has already begun to yield results that would have seemed implausible a decade ago. Protein folding problems that stumped researchers for fifty years yielded in months. Drug discovery pipelines that once took a decade can now run in weeks. Climate models that required the world's largest supercomputers now run on accelerated hardware that fits in a server rack.

        Yet the most profound changes may not be the ones we can point to on a benchmark chart. The deeper shift is in the nature of human expertise itself — what it means to know something, to be skilled at something, to contribute meaningfully to a domain of human endeavour.

        This is not a story of replacement, though the anxiety around that narrative is understandable and, in some cases, warranted. It is more accurately a story of augmentation — and of the particular kind of human judgment that becomes more valuable, not less, as our tools become more powerful.

        The professionals who will thrive are not necessarily those with the deepest domain knowledge alone, but those who can work fluidly across domains, who can ask the right questions of powerful tools, and who can exercise the kind of ethical and contextual judgment that no system can reliably replicate.
    """.trimIndent()

    private val articles = listOf(
        Article(
            id = 1,
            title = "The Quiet Revolution in Human-Computer Interaction",
            category = "Technology",
            author = "Sarah Chen",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=5",
            readTime = "6 min read",
            summary = "How spatial computing and ambient AI are dissolving the boundary between digital and physical work — and why most people haven't noticed yet.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/tech1/800/500",
            publishedAt = "2 hours ago",
            isFeatured = true
        ),
        Article(
            id = 2,
            title = "Inside the Lab That's Trying to Grow Coral Reefs from Scratch",
            category = "Science",
            author = "James Okafor",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=12",
            readTime = "8 min read",
            summary = "A team of marine biologists in Hawaii is using a combination of genetic sequencing and robotics to accelerate coral restoration by a factor of ten.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/ocean2/800/500",
            publishedAt = "4 hours ago"
        ),
        Article(
            id = 3,
            title = "Why the 15-Minute City Is Finally Having Its Moment",
            category = "Urban Design",
            author = "Lena Müller",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=9",
            readTime = "5 min read",
            summary = "After years of academic interest and political resistance, the urban planning concept that promises to put everything you need within a short walk is reshaping cities from Paris to Bogotá.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/city3/800/500",
            publishedAt = "Yesterday"
        ),
        Article(
            id = 4,
            title = "The Philosopher Who Predicted Our Attention Crisis",
            category = "Culture",
            author = "Marcus Webb",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=15",
            readTime = "10 min read",
            summary = "Long before smartphones, a mid-century thinker was warning that the medium itself, not the message, was the true agent of change. His ideas have never been more relevant.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/culture4/800/500",
            publishedAt = "Yesterday"
        ),
        Article(
            id = 5,
            title = "A New Map of the Gut Microbiome Changes Everything We Thought We Knew",
            category = "Health",
            author = "Dr. Priya Nair",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=20",
            readTime = "7 min read",
            summary = "Researchers have catalogued over 50,000 bacterial strains in the human gut — five times more than previously identified — and the findings challenge foundational assumptions about diet and disease.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/health5/800/500",
            publishedAt = "2 days ago"
        ),
        Article(
            id = 6,
            title = "The Economics of Loneliness: A \$1 Trillion Problem Nobody Is Solving",
            category = "Economics",
            author = "Amir Hosseini",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=33",
            readTime = "9 min read",
            summary = "Social isolation costs the global economy more than obesity and smoking combined, yet it receives a fraction of the policy attention. A look at why, and what we could do differently.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/econ6/800/500",
            publishedAt = "3 days ago"
        ),
        Article(
            id = 7,
            title = "Light Pollution Is Erasing the Night Sky. Here's Who Is Fighting Back.",
            category = "Environment",
            author = "Yuki Tanaka",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=44",
            readTime = "6 min read",
            summary = "A global network of amateur astronomers, Indigenous knowledge keepers, and lighting engineers are building a case — and a coalition — to reclaim the darkness.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/space7/800/500",
            publishedAt = "4 days ago"
        ),
        Article(
            id = 8,
            title = "The Craftspeople Keeping Ancient Techniques Alive in a Digital World",
            category = "Culture",
            author = "Elena Vasquez",
            authorAvatarUrl = "https://i.pravatar.cc/150?img=29",
            readTime = "5 min read",
            summary = "From Japanese sword-making to Andean weaving, a new generation of artisans is finding that the internet is not the enemy of tradition — it might be its best hope.",
            body = sampleBody,
            imageUrl = "https://picsum.photos/seed/craft8/800/500",
            publishedAt = "5 days ago"
        )
    )

    fun observeArticles(): Flow<List<Article>> = flow {
        delay(1200) // simulate network load
        emit(articles)
    }

    fun observeArticle(id: Long): Flow<Article?> = flow {
        emit(null) // loading
        delay(300)
        emit(articles.find { it.id == id })
    }

    fun getFeatured(): Article = articles.first { it.isFeatured }
    fun getArticles(): List<Article> = articles.filter { !it.isFeatured }
    fun getAllArticles(): List<Article> = articles
}
