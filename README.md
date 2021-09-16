# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix Part 1

### User Stories

#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [x] (2pts) Views should be responsive for both landscape/portrait mode.
   - [x] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [x] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.
   -Note: Can see this display if you use the MovieAdapter instead of the ComplexMovieAdapter in the MainActivity
- [ ] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
- [x] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

-Note: Full backdrop image is displayed for movies rated higher than 7.5 stars instead of 5

-Additional Feature: The user can tap on a movie's backdrop image to view the movies information (title, backdrop image, overview, and rating).

### App Walkthough GIF
Portrait Walkthrough
<img src="https://github.com/brandonschapiro/Flixster/blob/master/walkthrough-portrait.gif" width=250><br>

Landscape Walkthrough
<img src="https://github.com/brandonschapiro/Flixster/blob/master/walkthrough-landscape.gif" width=500><br>
### Notes
Describe any challenges encountered while building the app.

-Everything went relatively smoothly, most of my difficulties came from me just making silly errors like missing a break statement inside one of my switch statements in the ComplexMovieAdapter class or using getStringExtra instead of getIntExtra in the MovieInfoActivity.

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
