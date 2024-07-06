package com.spascoding.taskycourse.core.presentation.fab

import androidx.compose.ui.graphics.vector.ImageVector

data class FabItem(
    val id: Int,
    val icon: ImageVector? = null,
    val text: String = "",
)