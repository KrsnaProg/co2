package com.quantumai.co2.ui.alertsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quantumai.co2.R
import com.quantumai.co2.ui.colors.AppColors

data class AlertItemUi(
    val deviceName: String,
    val status: String,
    val date: String,
    val badge: String,
)

@Composable
fun AlertsScreen() {
    val alerts = listOf(
        AlertItemUi(
            deviceName = stringResource(R.string.alerts_feature_main_sensor),
            status = stringResource(R.string.alerts_feature_device_offline),
            date = "2025-05-11",
            badge = "M"
        ),
        AlertItemUi(
            deviceName = stringResource(R.string.alerts_feature_security_camera),
            status = stringResource(R.string.alerts_feature_internet_disconnected),
            date = "2025-05-11",
            badge = "S"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding() // Pushes content below the system status bar
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 18.dp) // Your +24dp requirement
    ) {
        Text(
            text = stringResource(R.string.alerts_feature_title),
            color = AppColors.primaryText,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(alerts) { alert ->
                AlertCard(alert = alert)
            }
        }
    }
}

@Composable
private fun AlertCard(alert: AlertItemUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, AppColors.primaryGray, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .background(AppColors.primaryLightBlue, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = alert.badge,
                color = AppColors.primaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = alert.deviceName,
                color = AppColors.primaryText,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = alert.status,
                color = AppColors.secondaryText,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = alert.date,
                color = AppColors.secondaryText,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        Box(
            modifier = Modifier
                .size(10.dp)
                .background(Color(0xFFDB143C), RoundedCornerShape(99.dp))
        )
    }
}
