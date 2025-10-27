package com.tencent.compose.sample

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tencent.compose.db.Person
import com.tencent.compose.db.PersonQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseHelper(
    private val personQueries: PersonQueries
) {
    fun getAllPeople(): Flow<List<Person>> = personQueries.selectAll().asFlow().mapToList(Dispatchers.Default)

    fun getPersonById(id: Long): Person? = personQueries.selectById(id).executeAsOneOrNull()

    suspend fun insertPerson(id: Long, name: String, age: Long) = withContext(Dispatchers.Default) {
        personQueries.insertPerson(id, name, age)
    }

    suspend fun updatePerson(id: Long, name: String, age: Long) = withContext(Dispatchers.Default) {
        personQueries.updatePerson(name, age, id)
    }

    suspend fun deletePerson(id: Long) = withContext(Dispatchers.Default) {
        personQueries.deletePerson(id)
    }

    suspend fun getAllPersonsList(): List<Person> = withContext(Dispatchers.Default) {
        personQueries.selectAll().executeAsList()
    }

    suspend fun deleteAllPersons() = withContext(Dispatchers.Default) {
        personQueries.deleteAll()
    }
}
