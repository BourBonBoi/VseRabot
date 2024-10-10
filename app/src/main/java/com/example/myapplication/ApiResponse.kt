package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("name") val name: String
)
data class VacancyDetails(
    @SerializedName("company") val company: Company
)

data class Vacancy(
    @SerializedName("vacancy") val details: VacancyDetails
)

data class VacanciesResponse(
    val results: Results
)

data class Results(
    val vacancies: List<Vacancy>
)