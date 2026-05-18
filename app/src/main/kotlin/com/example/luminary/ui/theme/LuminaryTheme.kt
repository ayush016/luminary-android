package com.example.luminary.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// ─── Layer 2: Semantic Tokens — Light ColorScheme (all 29 roles) ────────────

private val LightColorScheme = lightColorScheme(
    // Primary group
    primary             = Palette.Teal40,
    onPrimary           = Color.White,
    primaryContainer    = Palette.Teal90,
    onPrimaryContainer  = Palette.Teal10,
    inversePrimary      = Palette.Teal80,

    // Secondary group
    secondary               = Palette.BlueGrey40,
    onSecondary             = Color.White,
    secondaryContainer      = Palette.BlueGrey90,
    onSecondaryContainer    = Palette.BlueGrey10,

    // Tertiary group — warm amber for visual tension against teal
    tertiary                = Palette.Amber40,
    onTertiary              = Color.White,
    tertiaryContainer       = Palette.Amber90,
    onTertiaryContainer     = Palette.Amber10,

    // Error group
    error                   = Palette.Red40,
    onError                 = Color.White,
    errorContainer          = Palette.Red90,
    onErrorContainer        = Palette.Red10,

    // Surface group — 8 roles, each with a distinct purpose
    background              = Palette.Neutral99,
    onBackground            = Palette.Neutral10,
    surface                 = Palette.Neutral99,
    onSurface               = Palette.Neutral10,
    surfaceVariant          = Palette.NeutralVar90,
    onSurfaceVariant        = Palette.NeutralVar30,
    surfaceTint             = Palette.Teal40,
    inverseSurface          = Palette.Neutral22,
    inverseOnSurface        = Palette.Neutral95,

    // Outline group
    outline                 = Palette.NeutralVar50,
    outlineVariant          = Palette.NeutralVar80,
    scrim                   = Color.Black,
)

// ─── Layer 2: Semantic Tokens — Dark ColorScheme (all 29 roles) ─────────────
// Dark mode is NOT an inverted copy. Tones shift: primary moves from 40→80,
// containers move from 90→30, surfaces use deep neutral values.

private val DarkColorScheme = darkColorScheme(
    primary             = Palette.Teal80,
    onPrimary           = Palette.Teal20,
    primaryContainer    = Palette.Teal30,
    onPrimaryContainer  = Palette.Teal90,
    inversePrimary      = Palette.Teal40,

    secondary               = Palette.BlueGrey80,
    onSecondary             = Palette.BlueGrey20,
    secondaryContainer      = Palette.BlueGrey30,
    onSecondaryContainer    = Palette.BlueGrey90,

    tertiary                = Palette.Amber80,
    onTertiary              = Palette.Amber20,
    tertiaryContainer       = Palette.Amber30,
    onTertiaryContainer     = Palette.Amber90,

    error                   = Palette.Red80,
    onError                 = Palette.Red10,
    errorContainer          = Palette.Red40,
    onErrorContainer        = Palette.Red90,

    background              = Palette.Neutral10,
    onBackground            = Palette.Neutral90,
    surface                 = Palette.Neutral10,
    onSurface               = Palette.Neutral90,
    surfaceVariant          = Palette.NeutralVar30,
    onSurfaceVariant        = Palette.NeutralVar80,
    surfaceTint             = Palette.Teal80,
    inverseSurface          = Palette.Neutral90,
    inverseOnSurface        = Palette.Neutral22,

    outline                 = Palette.NeutralVar60,
    outlineVariant          = Palette.NeutralVar30,
    scrim                   = Color.Black,
)

// ─── Custom Extension Token: Gradient Colors ─────────────────────────────────

@Immutable
data class LuminaryGradientColors(
    val top:       Color = Color.Unspecified,
    val bottom:    Color = Color.Unspecified,
    val container: Color = Color.Unspecified,
)

val LocalLuminaryGradientColors = staticCompositionLocalOf { LuminaryGradientColors() }

private val LightGradientColors = LuminaryGradientColors(
    top       = Palette.Teal95,
    bottom    = Palette.BlueGrey90,
    container = Palette.Teal90,
)

private val DarkGradientColors = LuminaryGradientColors(
    top       = Palette.Teal10,
    bottom    = Palette.BlueGrey10,
    container = Palette.Teal20,
)

// ─── Custom Extension Token: Extended Semantic Colors ────────────────────────
// Material 3 only defines error. Warning and success are app-level additions.

@Immutable
data class LuminaryExtendedColors(
    val warning:              Color = Color.Unspecified,
    val onWarning:            Color = Color.Unspecified,
    val warningContainer:     Color = Color.Unspecified,
    val onWarningContainer:   Color = Color.Unspecified,
    val success:              Color = Color.Unspecified,
    val onSuccess:            Color = Color.Unspecified,
    val successContainer:     Color = Color.Unspecified,
    val onSuccessContainer:   Color = Color.Unspecified,
)

val LocalLuminaryExtendedColors = staticCompositionLocalOf { LuminaryExtendedColors() }

private val LightExtendedColors = LuminaryExtendedColors(
    warning             = Palette.Warning40,
    onWarning           = Color.White,
    warningContainer    = Palette.Warning90,
    onWarningContainer  = Palette.Warning10,
    success             = Palette.Green40,
    onSuccess           = Color.White,
    successContainer    = Palette.Green90,
    onSuccessContainer  = Palette.Green10,
)

private val DarkExtendedColors = LuminaryExtendedColors(
    warning             = Palette.Warning80,
    onWarning           = Palette.Warning10,
    warningContainer    = Palette.Warning40,
    onWarningContainer  = Palette.Warning90,
    success             = Palette.Green80,
    onSuccess           = Palette.Green10,
    successContainer    = Palette.Green40,
    onSuccessContainer  = Palette.Green90,
)

// ─── Theme Accessor Object ────────────────────────────────────────────────────
// Mirrors MaterialTheme pattern — LuminaryTheme.gradients, .extended

object LuminaryTheme {
    val gradients: LuminaryGradientColors
        @Composable get() = LocalLuminaryGradientColors.current
    val extended: LuminaryExtendedColors
        @Composable get() = LocalLuminaryExtendedColors.current
}

// ─── Theme Composable ─────────────────────────────────────────────────────────

@Composable
fun LuminaryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Priority 1: wallpaper-derived dynamic color (Android 12+)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        // Priority 2: brand dark palette (all 29 roles)
        darkTheme -> DarkColorScheme
        // Priority 3: brand light palette (all 29 roles)
        else -> LightColorScheme
    }

    val gradientColors = if (darkTheme) DarkGradientColors else LightGradientColors
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(
        LocalLuminaryGradientColors  provides gradientColors,
        LocalLuminaryExtendedColors  provides extendedColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography  = AppTypography,
            shapes      = AppShapes,
            content     = content
        )
    }
}
