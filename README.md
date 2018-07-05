# Commentator
Review comments of users

# Showcases the following technologies, and best practices and more
* MVVM with Android Architecture Components
* Survives configuration changes
* Adapts to a wide range of screen sizes
* Offline-first approach by employing Repository Pattern backed by Room Database
* More declarative app architecture using data binding
* Readable, maintainable, and generally more beautiful code with Kotlins functional programming aspects, and RxJava
* Clean Arch. using Dagger

# Highlights
* Review list is fetched from server and written to local db which is then read by viewmodel to update list with local data only. Enabling built-in offline support.
* Main unit testing and instrumented testing added. The rest could be added with ease.

# Future Development
* Edite review functionality will be added by changing the current Toast message with a Custom Dialog. It would be implemented and tested easily since all the information is available through the same view model written.
* Tests should cover 100%
* UI/UX should be updated after clearing out some functional details
* Minor changes should be made on repository pattern to make more generic modelling possible
