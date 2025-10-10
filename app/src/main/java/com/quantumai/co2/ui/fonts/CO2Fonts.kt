package com.quantumai.co2.ui.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.quantumai.co2.R

val Inter = FontFamily(
    Font(R.font.inter_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.inter_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.inter_extrabold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.inter_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.inter_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.inter_semibold, FontWeight.Black, FontStyle.Normal),
    Font(R.font.inter_extrabold, FontWeight.Black, FontStyle.Normal),
    Font(R.font.inter_medium, FontWeight.Black, FontStyle.Normal),
    Font(R.font.inter_regular, FontWeight.Black, FontStyle.Normal),
)