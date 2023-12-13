package com.D121211026.alquran.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211026.alquran.data.models.Ayah
import com.D121211026.alquran.data.models.Surah
import com.D121211026.alquran.ui.theme.SembarangMoDuluTheme

class DetailActivity : ComponentActivity() {

    private var selectedSurah: Surah? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedSurah = intent.getParcelableExtra("SURAH")
        setContent {
            SembarangMoDuluTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuranDetailScreen()
                }
            }
        }
    }

    @Composable
    fun QuranDetailScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Surah Details
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = selectedSurah?.name.toString(), style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = selectedSurah?.englishName.toString(), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Revelation Type: ${selectedSurah?.revelationType.toString()}", style = MaterialTheme.typography.bodySmall)

            // Ayah List
            Spacer(modifier = Modifier.height(16.dp))
            selectedSurah?.ayahs?.forEach { ayah ->
                QuranAyahItem(ayah = ayah)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    @Composable
    fun QuranAyahItem(ayah: Ayah) {
        // Display Ayah details here
        Text(text = "Ayah ${ayah.numberInSurah}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = ayah.text, style = MaterialTheme.typography.bodyMedium)
    }
}