package com.example.domain.models

data class EvolvesToEntity(
    val evolvesTo: List<EvolvesToEntity>?,
    val species: SpeciesEntity
)