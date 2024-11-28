package com.example.flightsearchapp.ui.screen.flightscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchapp.data.MockData
import com.example.flightsearchapp.model.Airport
import com.example.flightsearchapp.model.Favorite

@Composable
fun FlightResults(
    modifier: Modifier = Modifier,
    departureAirport: Airport,
    destinationList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (String, String) -> Unit
) {
    Column {
        LazyColumn(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            items(destinationList, key = { it.id }) { item ->
                val isFavorite = favoriteList.find { f ->
                    f.departureCode == departureAirport.code &&
                            f.destinationCode == item.code }

                FlightRow(
                    isFavorite = isFavorite != null,
                    departureAirportCode = departureAirport.code,
                    departureAirportName = departureAirport.name,
                    destinationAirportCode = item.code,
                    destinationAirportName = item.name,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Preview
@Composable
fun FlightResultsPreview() {
    val mockData = MockData.airports

    FlightResults(
        departureAirport = mockData[0],
        destinationList = mockData,
        favoriteList = emptyList(),
        onFavoriteClick = { _: String, _: String -> }
    )
}
