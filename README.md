My solution to the following assignment:


## Task

Develop an application that allows a user to:

- see the list of past SpaceX launches (```mission_name``` and the date
part of ```launch_date_utc```)
- select a launch from the list and display a small amount of
information about the launch, including an embedded video
(```links/video_link```).

Find API documentation here:

https://github.com/r-spacex/SpaceX-API


The application should make use of the REST API. However, it should
not simply fetch all the data once, rather ensure that the app displays
fresh data, so that it is always up-to-date as long as there is a data
connection. 


## About the project

- MVVM with ViewModel and LiveData
- Dagger 2
- Retrofit/Moshi
