package com.example.listycity3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize


@Composable
fun CityListScreen(
    cities: List<City>,
    updateCityName: String,
    updateCityProvince: String,
    selectedCity: City?,
    onAddCity: (City) -> Unit,
    onModUpdateCity: (String) -> Unit,
    onModUpdateProvince: (String) -> Unit,
    onUpdateCity: () -> Unit,
    onSelectCity: (City) -> Unit,
    onClearSelectedCity: () -> Unit,
    modifier: Modifier = Modifier
) {
    var newCityName by remember { mutableStateOf("") }
    var newProvinceName by remember { mutableStateOf("") }
    var showAddCityFields by remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxSize()) {
        Row( //Floating Action Button
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showAddCityFields = !showAddCityFields
                }
            ) {
                Text("+")
            }
        } //End Floating Action Button
        if (showAddCityFields) { //When showAddCityFields is true
            Row( //Add city text box
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = newProvinceName,
                    onValueChange = { newProvinceName = it },
                    label = { Text("Province") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier.padding(vertical = 12.dp),
                    onClick = {
                        if (newCityName.isNotBlank() && newProvinceName.isNotBlank()) {
                            onAddCity(
                                City(
                                    name = newCityName,
                                    province = newProvinceName
                                )
                            )
                            newCityName = ""
                            newProvinceName = ""
                            showAddCityFields = false
                        }
                    }
                ) {
                    Text("Add City")
                }
            } //End Add City Text Box
        } //End showAddCityFields is true
        LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(cities) { index, city ->
                if (city == selectedCity){ //When city being drawn is selected city for update, show an update box instead.
                    Row( //Add city text box
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = updateCityName,
                            onValueChange = { onModUpdateCity(it)},
                            label = { Text("City") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = updateCityProvince,
                            onValueChange = { onModUpdateProvince(it)},
                            label = { Text("Province") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            modifier = Modifier.padding(vertical = 12.dp),
                            onClick = {
                                    onUpdateCity()
                                    onModUpdateCity("")
                                    onModUpdateProvince("")
                                    onClearSelectedCity()
                            }
                        ) {
                            Text("Update City")
                        }
                    } //End Add City Text Box
                } else {
                    CityRow(city = city, selectedCity = selectedCity, onSelectCity)
                }
                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        } //End LazyColumn
    } //End Columns
} //End CityListScreen

@Composable
fun CityRow(city: City, selectedCity: City?, onSelectCity: (City) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .clickable(onClick = {onSelectCity(city)})
    ) {
        if (selectedCity == city) {
            Text(
                text = city.name,
                fontSize = 36.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = city.province,
                fontSize = 36.sp,
                modifier = Modifier.weight(1f)
            )
        } else {
            Text(
                text = city.name,
                fontSize = 30.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = city.province,
                fontSize = 30.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),
            updateCityName = "",
            updateCityProvince = "",
            selectedCity = null,
            onAddCity = {},
            onModUpdateCity = {},
            onModUpdateProvince = {},
            onUpdateCity = {},
            onSelectCity = {},
            onClearSelectedCity = {}
        )
    }
}