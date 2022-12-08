# totersapp - Technical Test Case #

### [👉 Video Demo for this task on youtube 👈](vedio link)

### Characters Search & Character Detail Features
 - [x] provide git commits as well ;).
 - [x] Use of design patterns and a good architecture is highly encouraged. Make Uncle Bob proud!
 - [x] Support API level 21 and above.
 - [x] The app is readable and clean.
 - [x] Error handling of the response.
 - [x] Business logic is unit tested. 
 
 <img src="gif link" width="300">
 
### Project Dependency Graph ###
![](image link)
- App module depend on (core + feature games list screen + feature game detail screen) : contains app start point it may be a splash screen.
- Core module depend on nothing  : contains all sharable data among different modules.
- Feature Games List Screen Module depend on core : contain games list screen feature related ui and other staffs.
- Feature Game Detail Screen Module depend on core : contain game detail screen feature related ui.

### Use domain specific language ###
- Make gradle scripts easy
- Support Kotlin for better dependencies management by Using Domain-Specific Languages.
- Support autocomplete

### Use Detect ###
- to make code style analysis and be sure follow best practice for writing kotlin code.

### Proguard To avoid 64k functions problem and get smaller apk ###
- Enables code shrinking, obfuscation, and optimization for only
- Enables resource shrinking, which is performed by the

### Modular architecture ###
- Speeds up builds
- Enable on demand delivery
- Simplify development
- Reuse modules across apps
- Experiment with new technologies
- Scale development teams
- Enables refactoring
- Simplifies test automation

### Clean architecture ###
- its a good choice to make project scalable and easy to test small parts  

### MVVM Inside each module 
- each developer inside android team has a free to select suitable architecture for his module. 

### Dependency injection with Hilt ###
- Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in our project.

### Moshi ###
- Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes

### Load and display paged data ###
- The Paging3 library provides powerful capabilities for loading and displaying paged data from a larger dataset.

### Enable AppCenter Pipelines ###
- Support CI so i can check different stages like build and run unit test 
before merge any branch to master branch.

### Firebase crashlytics ###
- to rack bugs even though app moved to production

### Data binding
- The Data Binding Library automatically generates the classes required to bind the views in the layout with your data objects.

### leakCanary 
- to detect memory leak through debugging process that will improve app performance as well. 

### Truth For unit test its recommended from google
- write unit test for [Search Validator Class](https://github.com/MostafaAnter/totersapp/blob/master/feature_characters_list/src/test/java/app/anter/feature_characters_list/SearchValidatorTest.kt)
- write integrated test for ensure [Database work fine](https://github.com/MostafaAnter/totersapp/blob/master/core_data/src/androidTest/java/app/anter/core_data/AppDatabaseTest.kt)
  <img src="https://raw.githubusercontent.com/MostafaAnter/totersapp/master/Screen%20Shot%202022-01-21%20at%2011.00.28%20PM.png" width="500">
  
### Reactive programming (Coroutines)
Coroutines is Google recommended solution for asynchronous programming on Android. Noteworthy features include the following:

- Lightweight: You can run many coroutines on a single thread due to support for suspension, which doesn't block the thread where the coroutine is running. Suspending saves memory over blocking while supporting many concurrent operations.
- Fewer memory leaks: Use structured concurrency to run operations within a scope.
- Built-in cancellation support: Cancellation is propagated automatically through the running coroutine hierarchy.
- Jetpack integration: Many Jetpack libraries include extensions that provide full coroutines support. Some libraries also provide their own coroutine scope that you can use for structured concurrency.

### Local database using Room ###
- Compile-time verification of SQL queries.
- Convenience annotations that minimize repetitive and error-prone boilerplate code.
- Streamlined database migration paths.

### Paging 3 ###
- In-memory caching for your paged data. This ensures that your app uses system resources efficiently while working with paged data.
- Built-in request deduplication, ensuring that your app uses network bandwidth and system resources efficiently.
- Configurable RecyclerView adapters that automatically request data as the user scrolls toward the end of the loaded data.
- First-class support for Kotlin coroutines and Flow, as well as LiveData and RxJava.
- Built-in support for error handling, including refresh and retry capabilities.


### License
    Copyright [2022] [Mostafa Anter]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and limitations under the License.

