# Luminary — Android Sample App

> **This project was built entirely using the [android-lead-agent-skills](https://github.com/ayush016/android-lead-agent-skills) skill.**
> Every pattern, architecture decision, animation, and design system choice in this codebase follows the guidance documented in that skill library. It serves as a living proof-of-concept for what the skill produces when applied end-to-end to a real Android project.

A beautiful news reader app demonstrating production-grade Android UI patterns: shared element transitions, Material 3 design system, skeleton loading, staggered animations, and Clean Architecture.

---

## Built With the Android Lead Agent Skill

This sample is the direct output of applying **[android-lead-agent-skills](https://github.com/ayush016/android-lead-agent-skills)** — a comprehensive Android engineering skill library covering architecture, Jetpack Compose, animations, performance, security, and more.

Every file in this project traces back to a specific section of that skill:

| What you see in the app | Skill reference used |
|-------------------------|----------------------|
| Shared element transitions (image + title) | [`shared-element-transitions.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/shared-element-transitions.md) |
| Material 3 teal theme + serif typography | [`compose-ui-system.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/compose-ui-system.md) |
| Skeleton shimmer loading states | [`compose-ui-system.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/compose-ui-system.md) |
| Spring + stagger animations | [`motion-and-animation.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/motion-and-animation.md) |
| Type-safe navigation + SharedTransitionLayout | [`navigation.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/navigation.md) |
| StateFlow + SharedFlow(replay=0) | [`architecture.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/architecture.md) |
| Sealed UI state (Loading/Ready/Error) | [`architecture.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/architecture.md) |
| Offline-first Flow repository | [`data-layer.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/data-layer.md) |
| Hilt DI graph + @HiltViewModel | [`architecture.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/architecture.md) |
| Coil3 image loading + SubcomposeAsyncImage | [`image-loading.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/image-loading.md) |
| Edge-to-edge + WindowInsets | [`adaptive-layouts.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/adaptive-layouts.md) |
| Convention plugins + version catalog | [`build-and-modules.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/build-and-modules.md) |
| Canvas open-book illustration (Bookmarks) | [`compose-ui-system.md`](https://github.com/ayush016/android-lead-agent-skills/blob/main/references/compose-ui-system.md) |

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
| AGP | 8.5.2 | Android Gradle Plugin |
| Gradle | 8.7 (pinned via wrapper) | Build system |
| Jetpack Compose | BOM 2024.09.00 | UI |
| Navigation Compose | 2.8.4 | Type-safe navigation |
| Hilt | 2.52 | Dependency injection |
| Coil3 | 3.0.4 | Image loading |
| Lifecycle | 2.8.7 | `collectAsStateWithLifecycle` |
| kotlinx.serialization | 1.7.3 | Navigation route serialization |
| KSP | 2.0.21-1.0.25 | Kotlin Symbol Processing (Hilt) |

---

## Setup

1. Clone: `git clone https://github.com/ayush016/luminary-android`
2. Open in Android Studio Ladybug (2024.2.1) or newer
3. Let Android Studio download Gradle 8.7 via the wrapper (first sync only)
4. Run on a device or emulator with **API 26+** (Android 8.0+)
5. Images load from `picsum.photos` — internet connection required

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
