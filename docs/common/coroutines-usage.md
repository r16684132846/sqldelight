```kotlin
val players: Flow<List<HockeyPlayer>> = 
  playerQueries.selectAll()
    .asFlow()
    .mapToList(Dispatchers.IO)
```

This flow emits the query result, and emits a new result every time the database changes for that query.