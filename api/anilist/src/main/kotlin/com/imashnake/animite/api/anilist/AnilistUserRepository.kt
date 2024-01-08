package com.imashnake.animite.api.anilist

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnilistUserRepository @Inject constructor(
    private val apolloClient: ApolloClient
) {
    fun fetchViewer(): Flow<Result<UserQuery.User?>> {
        return apolloClient
            .query(UserQuery())
            .fetchPolicy(FetchPolicy.CacheAndNetwork).toFlow()
            .asResult { it.user }
    }
}
