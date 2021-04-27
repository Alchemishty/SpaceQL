package com.example.spaceql.Model.Network

import com.apollographql.apollo.ApolloClient

val apolloClient = ApolloClient.builder()
        .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com")
        .build()