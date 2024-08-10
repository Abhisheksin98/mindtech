package com.example.mindtech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindtech.ui.theme.MindtechTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindtechTheme {
                ImageCarouselWithList()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ImageCarouselWithList() {

    val images = listOf(
        R.drawable.car,
        R.drawable.city,
        R.drawable.forest,
        R.drawable.river,
        R.drawable.fruit
    )

    val categories = listOf(
        Category.Cars,
        Category.Cities,
        Category.Forests,
        Category.Rivers,
        Category.Fruits
    )

    val pagerState = rememberPagerState(pageCount = {images.size})
    val currentPage by remember { derivedStateOf { pagerState.currentPage } }
    var showBottomSheet by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val currentList = DataProvider.getDataByCategory(categories[currentPage])
    val filteredList = currentList.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    fun getTopThreeCharacters(list: List<ListItems>): List<Pair<Char, Int>> {
        val charFrequency = mutableMapOf<Char, Int>()
        list.forEach { item ->
            item.title.lowercase().forEach { char ->
                if (char.isLetter()) {
                    charFrequency[char] = charFrequency.getOrDefault(char, 0) + 1
                }
            }
        }
        return charFrequency.toList().sortedByDescending { it.second }.take(3)
    }

    // Top 3 characters in the filtered list
    val topThreeCharacters = getTopThreeCharacters(filteredList)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, bottom = 10.dp)
        ) {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                            .height(220.dp)
                    ) { page ->
                        Image(
                            painter = painterResource(id = images[page]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp)
                    ) {
                        repeat(images.size) { index ->
                            val color =
                                if (pagerState.currentPage == index) Color.Black else Color.Gray
                            Box(
                                modifier = Modifier
                                    .size(15.dp)
                                    .padding(4.dp)
                                    .background(
                                        color = color, shape = CircleShape
                                    )
                            )
                        }
                    }
                }
            }

            stickyHeader {
                // Search Box
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, bottom = 16.dp),
                    placeholder = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )
            }

                items(filteredList) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(id = images[currentPage]),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(shape = RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(verticalArrangement = Arrangement.SpaceAround) {
                                Text(
                                    text = item.title,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier,
                                    color = Color.Black
                                )
                                Text(
                                    text = item.subtitle,
                                    modifier = Modifier,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
        }
        val iconPainter: Painter = painterResource(id = R.drawable.dots)
        FloatingActionButton(
            onClick = { showBottomSheet = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.Blue,
            shape = CircleShape
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = "Show count",
                tint = Color.White
            )
        }
    }

    // Bottom Sheet Dialog
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Item Count",
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Total items in list: ${filteredList.size}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Top 3 Characters:",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                topThreeCharacters.forEach { (char, count) ->
                    Text(
                        text = "$char = $count",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { showBottomSheet = false }
                ) {
                    Text("Close")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MindtechTheme {
        ImageCarouselWithList()
    }
}