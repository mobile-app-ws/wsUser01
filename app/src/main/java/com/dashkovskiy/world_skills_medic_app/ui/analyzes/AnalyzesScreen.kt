package com.dashkovskiy.world_skills_medic_app.ui.analyzes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dashkovskiy.world_skills_medic_app.R
import com.dashkovskiy.world_skills_medic_app.api.Catalog
import com.dashkovskiy.world_skills_medic_app.api.News
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun AnalyzesScreenPreview() {
    AnalyzesScreen()
}

@Composable
fun AnalyzesScreen(
    modifier : Modifier = Modifier,
    viewModel: AnalyzesViewModel = getViewModel()
) {
    val state by viewModel.viewState.collectAsState()

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xff1A6FEE)
            )
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            MedicSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = state.searchValue,
                placeholder = "Искать анализы",
                onValuesChanged = viewModel::setSearch
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "Акции и новости",
                color = Color(0xff939396),
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.news) { news ->
                    NewsCard(news = news)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = "Каталог анализов",
                color = Color(0xff939396),
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W600
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.categories.toList()) { category ->
                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .background(
                                color =
                                if (category == state.selectedCategory)
                                    Color(0xff1A6FEE)
                                else
                                    Color(0xFFF5F5F9),
                                shape = RoundedCornerShape(20.dp)

                            )
                            .clickable { viewModel.setCategory(category) }
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = category,
                            color = if (category == state.selectedCategory)
                                Color.White
                            else
                                Color(0xff939396),
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.W500
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                state.catalog.forEach { catalog ->
                    CatalogCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(335f / 136f),
                        catalog = catalog
                    )
                }
            }
        }
    }
}


@Composable
fun MedicSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValuesChanged: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = onValuesChanged,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .height(48.dp)
                    .background(
                        color = Color(0xffF5F5F9),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFFEBEBEB),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color(0xff7E7E9A)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box {
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Black.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun CatalogCard(
    modifier: Modifier = Modifier,
    catalog: Catalog,
    onAddClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFF4F4F4),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = catalog.name,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W500
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = catalog.timeResult,
                    color = Color(0xff939396),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.W600
                    )
                )
                Text(
                    text = "${catalog.price} ₽",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 17.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W600
                    )
                )
            }
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 16.dp),
                onClick = onAddClick,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff1A6FEE),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Далее")
            }
        }
    }
}

@Composable
fun NewsCard(news: News) {
    Row(
        modifier = Modifier
            .requiredSize(width = 270.dp, height = 152.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xffCDE3FF), Color(0xFF76B3FF))
                ),
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, top = 16.dp, bottom = 12.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = news.name,
                color = Color.White,
                maxLines = 2,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.W800
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = news.description,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400
                )
            )
            Text(
                text = "${news.price} ₽",
                color = Color.White,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.W800
                )
            )
        }
        AsyncImage(
            modifier = Modifier.weight(1f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(news.image)
                .crossfade(true)
                .build(),
            alignment = Alignment.CenterEnd,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}