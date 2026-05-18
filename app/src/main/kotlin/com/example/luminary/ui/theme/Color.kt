package com.example.luminary.ui.theme

import androidx.compose.ui.graphics.Color

// ─── Layer 1: Primitive Tokens ──────────────────────────────────────────────
// Internal only. Nothing outside this package uses these directly.
// Tone scale: 10 = darkest, 99 = lightest (Material 3 convention)

internal object Palette {

    // Teal family (primary)
    val Teal10  = Color(0xFF001F24)
    val Teal20  = Color(0xFF00363D)
    val Teal30  = Color(0xFF004F57)
    val Teal40  = Color(0xFF006874)
    val Teal80  = Color(0xFF4DD9E8)
    val Teal90  = Color(0xFF97F0FF)
    val Teal95  = Color(0xFFCBF8FF)
    val Teal99  = Color(0xFFF2FEFF)

    // Blue-grey family (secondary)
    val BlueGrey10  = Color(0xFF051F23)
    val BlueGrey20  = Color(0xFF1C3439)
    val BlueGrey30  = Color(0xFF324B50)
    val BlueGrey40  = Color(0xFF4A6267)
    val BlueGrey80  = Color(0xFFB1CBD0)
    val BlueGrey90  = Color(0xFFCDE7EC)

    // Amber family (tertiary — warm accent for visual balance)
    val Amber10  = Color(0xFF251A00)
    val Amber20  = Color(0xFF3D2E00)
    val Amber30  = Color(0xFF564400)
    val Amber40  = Color(0xFF745B00)
    val Amber80  = Color(0xFFEFC348)
    val Amber90  = Color(0xFFFFDF9D)

    // Neutral (surface & background tones)
    val Neutral10  = Color(0xFF191C1D)
    val Neutral12  = Color(0xFF1D2021)
    val Neutral17  = Color(0xFF272B2C)
    val Neutral22  = Color(0xFF2E3132)
    val Neutral87  = Color(0xFFDEE1E2)
    val Neutral90  = Color(0xFFE1E3E4)
    val Neutral92  = Color(0xFFE4E6E8)
    val Neutral94  = Color(0xFFE9ECEE)
    val Neutral95  = Color(0xFFEFF2F3)
    val Neutral96  = Color(0xFFF2F4F5)
    val Neutral98  = Color(0xFFF5F8F9)
    val Neutral99  = Color(0xFFFAFDFE)

    // Neutral variant (surfaceVariant tones)
    val NeutralVar30  = Color(0xFF3F4849)
    val NeutralVar50  = Color(0xFF6F797A)
    val NeutralVar60  = Color(0xFF89929A)
    val NeutralVar80  = Color(0xFFBFC8CA)
    val NeutralVar90  = Color(0xFFDBE4E6)

    // Error (fixed across all brands — from Material baseline)
    val Red10  = Color(0xFF410002)
    val Red40  = Color(0xFFBA1A1A)
    val Red80  = Color(0xFFFFB4AB)
    val Red90  = Color(0xFFFFDAD6)

    // Extended: Warning (amber)
    val Warning10  = Color(0xFF1F1100)
    val Warning40  = Color(0xFF795900)
    val Warning80  = Color(0xFFEDB900)
    val Warning90  = Color(0xFFFFDFA0)

    // Extended: Success (green)
    val Green10  = Color(0xFF00210B)
    val Green40  = Color(0xFF1A6B2F)
    val Green80  = Color(0xFF9CD7AE)
    val Green90  = Color(0xFFB7F4C8)
}
