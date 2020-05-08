# Sandwich Club
[Udacity Grow with Google](https://www.udacity.com/grow-with-google) [Android Developer Nanodegree Program](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801)

## Overview
The task was to complete the Sandwich Club app, make it show the details of each sandwich once it is selected. The starter code was taken from [here](https://github.com/udacity/sandwich-club-starter-code).

## Screenshots
<p align="center">
    <img src="screenshots/Screenshot_1.png?raw=true" width=275 />
    <img src="screenshots/Screenshot_2.png?raw=true" width=275 />
</p>

## How to work with the project
Just clone this repository or download as an archive and import in Android Studio.

## Project Requirements

### Common
- [x] App is written solely in the Java Programming Language
- [x] App conforms to common standards found in the [Android Nanodegree General Project Guidelines](http://udacity.github.io/android-nanodegree-guidelines/core.html)
- [x] App utilizes stable release versions of all libraries, Gradle, and Android Studio

### Core Functionality
- [x] JSON data is parsed correctly to a Sandwich object in JsonUtils
- [x] JSON is parsed without using 3rd party libraries
- [x] DetailActivity shows all Sandwich details correctly
- [x] Detail layout includes a ScrollView so all the details are visible in small screen devices
- [x] Sandwich details are shown in a sensible layout

## What have I learnt?
- Parsing JSON to a model object
- Designing an activity layout
- Populating fields in the layout accordingly
- Handling inconsistent data

## Libraries
* [AndroidX](https://developer.android.com/jetpack/androidx/) previously known as *'Android support Library'*
    * [ConstraintLayout](https://developer.android.com/training/constraint-layout) allows to create large and complex layouts
    * [DataBinding](https://developer.android.com/topic/libraries/data-binding/) allows to bind UI components in layouts to data sources in app
* [Picasso](https://square.github.io/picasso/) allows for hassle-free image loading

## License
    Copyright 2020 demur

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.