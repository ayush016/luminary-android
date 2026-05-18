package com.example.luminary.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// ─── Brand Brush System ───────────────────────────────────────────────────────
// All gradients read from theme tokens — never inline hex values in composables.
//
// Photo overlay rule: text on image scrim uses colorScheme.scrim (= black) with
// opacity. This is the correct M3 token for image overlays, not Color.Black.
// The on-scrim text colour is Color.White — acceptable for photo overlays
// (distinct from text-on-surface where onSurface / onSurfaceVariant must be used).

object AppBrushes {

    // Featured article card: transparent → scrim (dark) gradient for text readability
    @Composable
    fun heroScrim(): Brush = Brush.verticalGradient(
        colorStops = arrayOf(
            0.00f to Color.Transparent,
            0.35f to MaterialTheme.colorScheme.scrim.copy(alpha = 0.20f),
            1.00f to MaterialTheme.colorScheme.scrim.copy(alpha = 0.82f),
        )
    )

    // Article grid card: lighter bottom scrim for category chip legibility
    @Composable
    fun cardImageScrim(): Brush = Brush.verticalGradient(
        colorStops = arrayOf(
            0.0f to Color.Transparent,
            1.0f to MaterialTheme.colorScheme.scrim.copy(alpha = 0.45f),
        )
    )

    // Detail screen: image fades into the surface background below it
    @Composable
    fun detailImageFade(): Brush = Brush.verticalGradient(
        colorStops = arrayOf(
            0.0f to Color.Transparent,
            1.0f to MaterialTheme.colorScheme.surface,
        )
    )

    // Screen hero background (top-to-bottom brand gradient tint)
    @Composable
    fun screenBackground(): Brush {
        val colors = LuminaryTheme.gradients
        return Brush.verticalGradient(
            colors = listOf(colors.top, colors.bottom)
        )
    }

    // Radial glow behind hero elements
    @Composable
    fun primaryGlow(): Brush = Brush.radialGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
            Color.Transparent,
        ),
        radius = 700f
    )

    // Animated gradient applied to headline text via drawWithCache + BlendMode.SrcAtop
    @Composable
    fun brandText(): Brush = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.tertiary,
        )
    )
}
