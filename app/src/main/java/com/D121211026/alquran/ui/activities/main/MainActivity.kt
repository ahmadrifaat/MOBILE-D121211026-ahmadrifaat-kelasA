package com.D121211026.alquran.ui.activities.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.D121211026.alquran.data.models.Ayah
import com.D121211026.alquran.data.models.Surah
import com.D121211026.alquran.ui.theme.SembarangMoDuluTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SembarangMoDuluTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val quranViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                    QuranListScreen(quranViewModel.mainUiState)
                }
            }
        }
    }

    @Composable
    private fun QuranListScreen(quranUiState: MainUiState, modifier: Modifier = Modifier) {
        when (quranUiState) {
            is MainUiState.Loading -> Text(text = "Loading Quranic Verses", fontSize = 16.sp)
            is MainUiState.Error -> Text(text = "Error Occurred", fontSize = 16.sp)
            is MainUiState.Success -> QuranicVersesList(quranUiState.photos)
        }
    }

    @Composable
    fun QuranicVersesList(surahs: List<Surah>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(surahs) { surah ->
                SurahItem(surah = surah)
            }
        }
    }

    @Composable
    fun SurahItem(surah: Surah) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .clickable {
                    // Handle click action if needed
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Surah Details
                Text(
                    text = "Surah: ${surah.name}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "English Name: ${surah.englishName}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Revelation Type: ${surah.revelationType}",
                    style = MaterialTheme.typography.bodySmall
                )

                // Ayahs List
                Spacer(modifier = Modifier.height(8.dp))
                QuranicAyahsList(ayahs = surah.ayahs)
            }
        }
    }

    @Composable
    fun QuranicAyahsList(ayahs: List<Ayah>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(ayahs) { ayah ->
                AyahItem(ayah = ayah)
            }
        }
    }

    @Composable
    fun AyahItem(ayah: Ayah) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Ayah Text
                Text(
                    text = "Ayah ${ayah.numberInSurah}: ${ayah.text}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}