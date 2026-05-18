# Luminary — Android Sample App

A beautiful news reader app demonstrating production-grade Android UI patterns: shared element transitions, Material 3 design system, skeleton loading, staggered animations, and Clean Architecture.

Built as a companion to the [android-lead-agent-skills](https://github.com/ayush016/android-lead-agent-skills) skill library.

---

## Screenshots & Features

### Home Screen
- Featured hero card with full-bleed image and gradient overlay
- 2-column article grid with staggered entry animations (60ms per item)
- Skeleton shimmer loading states mirroring exact content geometry

### Article Detail
- Shared element transition: image and title flow from card to detail
- Back button fades in after transition completes (180ms delay)
- Full scrollable article body in serif font

### Discover
- Category chip filtering with FlowRow
- Trending horizontal scroll
- Search bar

### Bookmarks
- Custom Canvas open-book illustration
- Empty state with call-to-action

---

## Key Patterns Demonstrated

| Pattern | Where |
|---------|-------|
| `sharedBounds()` container transform | `FeaturedArticleCard` |
| `sharedElement()` image transition | `HomeScreen` → `DetailScreen` |
| `sharedElement()` text continuation | Article title in list → heading in detail |
| `animateEnterExit` for back button | `DetailScreen` |
| `SharedTransitionLayout` wrapping `NavHost` | `AppNavigation.kt` |
| `StateFlow` + `SharedFlow` | `HomeViewModel`, `DetailViewModel` |
| `collectAsStateWithLifecycle()` | Both screens |
| `SavedStateHandle.toRoute()` | `DetailViewModel` |
| `stateIn(WhileSubscribed(5000))` | `DetailViewModel` |
| Sealed UI state | `HomeUiState`, `DetailUiState` |
| Skeleton shimmer matching layout | `HomeLoadingSkeleton`, `DetailLoadingSkeleton` |
| Staggered list entry | `HomeContent` grid items |
| Material 3 dynamic color | `LuminaryTheme` |
| Edge-to-edge with proper insets | `MainActivity`, all screens |

---

## Tech Stack

| Library | Version | Purpose |
|---------|---------|---------|
| Jetpack Compose | BOM 2024.12.01 | UI |
| Navigation Compose | 2.8.5 | Type-safe navigation |
| Hilt | 2.52 | Dependency injection |
| Coil3 | 3.0.4 | Image loading |
| Lifecycle | 2.8.7 | `collectAsStateWithLifecycle` |
| kotlinx.serialization | 1.7.3 | Navigation route serialization |

---

## Setup

1. Clone: `git clone https://github.com/ayush016/luminary-android`
2. Open in Android Studio Ladybug (2024.2.1) or newer
3. Run on a device or emulator with API 24+
4. Images load from `picsum.photos` — internet connection required

No API keys or configuration needed.

---

## Architecture

```
app/src/main/kotlin/com/example/luminary/
├── data/
│   ├── model/Article.kt           — data class
│   └── repository/ArticleRepository.kt  — @Singleton, Flow-based
├── feature/
│   ├── home/     — HomeScreen, HomeViewModel, HomeUiState
│   ├── detail/   — DetailScreen, DetailViewModel
│   ├── discover/ — DiscoverScreen
│   └── bookmarks/— BookmarksScreen
├── navigation/
│   ├── Routes.kt         — @Serializable route objects
│   └── AppNavigation.kt  — NavHost wrapped in SharedTransitionLayout
├── ui/
│   ├── theme/    — Color, Type, LuminaryTheme
│   └── component/— ShimmerBox, CategoryChip
├── LuminaryApp.kt   — @HiltAndroidApp + Coil singleton
└── MainActivity.kt  — enableEdgeToEdge() entry point
```
