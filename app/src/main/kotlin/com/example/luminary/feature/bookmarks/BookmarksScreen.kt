package com.example.luminary.feature.bookmarks

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BookmarksScreen(
    onDiscoverClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bookmarks",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            textAlign = TextAlign.Start
        )

        OpenBookIllustration(
            primaryColor = MaterialTheme.colorScheme.primary,
            surfaceVariantColor = MaterialTheme.colorScheme.surfaceVariant,
            outlineColor = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your reading list is empty",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Save articles to read them later, even offline.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onDiscoverClick,
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text(
                text = "Discover stories",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun OpenBookIllustration(
    primaryColor: Color,
    surfaceVariantColor: Color,
    outlineColor: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawOpenBook(
            primaryColor = primaryColor,
            surfaceVariantColor = surfaceVariantColor,
            outlineColor = outlineColor
        )
    }
}

private fun DrawScope.drawOpenBook(
    primaryColor: Color,
    surfaceVariantColor: Color,
    outlineColor: Color
) {
    val w = size.width
    val h = size.height
    val cx = w / 2f
    val cy = h / 2f

    // Book dimensions
    val bookW = w * 0.82f
    val bookH = h * 0.62f
    val bookLeft = (w - bookW) / 2f
    val bookTop = cy - bookH / 2f + h * 0.04f

    // Shadow / drop shadow ellipse behind book
    drawOval(
        color = outlineColor.copy(alpha = 0.12f),
        topLeft = Offset(bookLeft + bookW * 0.08f, bookTop + bookH + 8.dp.toPx()),
        size = Size(bookW * 0.84f, 14.dp.toPx())
    )

    // Left page
    val leftPath = Path().apply {
        addRoundRect(
            RoundRect(
                rect = Rect(
                    left = bookLeft,
                    top = bookTop,
                    right = cx - 2.dp.toPx(),
                    bottom = bookTop + bookH
                ),
                topLeftCornerRadius = CornerRadius(12.dp.toPx()),
                topRightCornerRadius = CornerRadius(0f),
                bottomRightCornerRadius = CornerRadius(0f),
                bottomLeftCornerRadius = CornerRadius(12.dp.toPx())
            )
        )
    }
    drawPath(path = leftPath, color = surfaceVariantColor)
    drawPath(
        path = leftPath,
        color = outlineColor.copy(alpha = 0.25f),
        style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    )

    // Right page
    val rightPath = Path().apply {
        addRoundRect(
            RoundRect(
                rect = Rect(
                    left = cx + 2.dp.toPx(),
                    top = bookTop,
                    right = bookLeft + bookW,
                    bottom = bookTop + bookH
                ),
                topLeftCornerRadius = CornerRadius(0f),
                topRightCornerRadius = CornerRadius(12.dp.toPx()),
                bottomRightCornerRadius = CornerRadius(12.dp.toPx()),
                bottomLeftCornerRadius = CornerRadius(0f)
            )
        )
    }
    drawPath(path = rightPath, color = surfaceVariantColor)
    drawPath(
        path = rightPath,
        color = outlineColor.copy(alpha = 0.25f),
        style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    )

    // Spine / center crease
    drawLine(
        color = outlineColor.copy(alpha = 0.35f),
        start = Offset(cx, bookTop),
        end = Offset(cx, bookTop + bookH),
        strokeWidth = 3.dp.toPx(),
        cap = StrokeCap.Round
    )

    // Lines on left page (simulating text)
    val lineStroke = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round)
    val lineColor = primaryColor.copy(alpha = 0.25f)
    val lineStartX = bookLeft + 16.dp.toPx()
    val lineEndX = cx - 12.dp.toPx()
    val lineStartY = bookTop + 20.dp.toPx()
    val lineSpacing = 11.dp.toPx()
    val lineCount = 6
    repeat(lineCount) { i ->
        val y = lineStartY + i * lineSpacing
        val endX = if (i == lineCount - 1) lineStartX + (lineEndX - lineStartX) * 0.55f else lineEndX
        drawLine(
            color = lineColor,
            start = Offset(lineStartX, y),
            end = Offset(endX, y),
            strokeWidth = 1.5.dp.toPx(),
            cap = StrokeCap.Round
        )
    }

    // Lines on right page
    val rLineStartX = cx + 12.dp.toPx()
    val rLineEndX = bookLeft + bookW - 16.dp.toPx()
    repeat(lineCount) { i ->
        val y = lineStartY + i * lineSpacing
        val endX = if (i == lineCount - 1) rLineStartX + (rLineEndX - rLineStartX) * 0.4f else rLineEndX
        drawLine(
            color = lineColor,
            start = Offset(rLineStartX, y),
            end = Offset(endX, y),
            strokeWidth = 1.5.dp.toPx(),
            cap = StrokeCap.Round
        )
    }

    // Bookmark ribbon on right page top-right corner
    val ribbonX = bookLeft + bookW - 20.dp.toPx()
    val ribbonTopY = bookTop
    val ribbonBottomY = bookTop + 30.dp.toPx()
    val ribbonW = 10.dp.toPx()
    val ribbonPath = Path().apply {
        moveTo(ribbonX, ribbonTopY)
        lineTo(ribbonX + ribbonW, ribbonTopY)
        lineTo(ribbonX + ribbonW, ribbonBottomY)
        lineTo(ribbonX + ribbonW / 2f, ribbonBottomY - 7.dp.toPx())
        lineTo(ribbonX, ribbonBottomY)
        close()
    }
    drawPath(path = ribbonPath, color = primaryColor.copy(alpha = 0.7f))

    // Small highlight dot on ribbon
    drawCircle(
        color = primaryColor.copy(alpha = 0.9f),
        radius = 2.5.dp.toPx(),
        center = Offset(ribbonX + ribbonW / 2f, ribbonTopY + 9.dp.toPx())
    )
}
