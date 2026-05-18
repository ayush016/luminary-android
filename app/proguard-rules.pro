# Kotlin serialization (for type-safe navigation routes)
-keepattributes *Annotation*, InnerClasses
-keepclassmembers @kotlinx.serialization.Serializable class * {
    *** Companion;
    *** serializer();
    <init>(...);
}
-keepclassmembers class kotlinx.serialization.** { *; }

# Hilt
-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel

# Coil
-dontwarn okhttp3.**

# Keep navigation routes
-keep @kotlinx.serialization.Serializable class com.example.luminary.navigation.** { *; }
