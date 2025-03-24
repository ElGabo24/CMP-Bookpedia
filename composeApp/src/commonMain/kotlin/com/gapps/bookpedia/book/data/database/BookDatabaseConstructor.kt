@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.gapps.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDatabase> {

    override fun initialize(): FavoriteBookDatabase

}