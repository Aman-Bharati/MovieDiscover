# MovieDiscover

🎬 Movie & TV Show Discovery App

Android | Jetpack Compose | Watchmode API

📌 Project Overview

The Movie & TV Show Discovery App is an Android application built using Jetpack Compose that allows users to browse movies and TV shows fetched from the Watchmode API.
The app demonstrates modern Android development practices including MVVM architecture, Retrofit networking, RxJava (Single.zip), Koin dependency injection, and shimmer loading effects.

🎯 Objective

To create a modern Android application that:

Fetches movies and TV shows from an external API

Displays content using Jetpack Compose UI

Uses MVVM architecture

Handles loading states and errors gracefully

✨ Features
🏠 Home Screen

Fetches Movies and TV Shows from Watchmode API

Uses Single.zip to load both datasets simultaneously

Tab-based toggle to switch between Movies and TV Shows

Displays posters, title, and release year

Shimmer effect while data is loading

📄 Details Screen

Shows full details of a selected movie or TV show:

Title

Description

Release year

Poster image

Shimmer loading effect during API fetch

⚠️ Error Handling

Network or API errors are displayed using Snackbar

Retry option available

🛠 Tech Stack
Technology	Usage
Kotlin	Programming language
Jetpack Compose	UI development
Retrofit	API networking
RxJava (RxKotlin)	Async operations
Single.zip	Parallel API calls
Koin	Dependency Injection
MVVM	Architecture pattern
Coil	Image loading
Watchmode API	Movie & TV data
🧱 Architecture (MVVM)
UI (Compose Screens)
      ↓
ViewModel
      ↓
Repository
      ↓
Retrofit API (Watchmode)

Layers:

UI Layer → Compose screens (HomeScreen, DetailsScreen)

ViewModel Layer → Handles UI state and business logic

Repository Layer → Fetches data from API

Data Layer → Models & API interfaces

🔗 API Integration

Watchmode API is used to fetch:

Movies list

TV Shows list

Title details

Single.zip is used to fetch Movies and TV Shows in parallel

Example:

Single.zip(
    api.getMovies(apiKey),
    api.getTvShows(apiKey)
)

⏳ Loading States

Shimmer effect is shown:

On Home Screen while loading list

On Details Screen while loading item details

📦 Dependency Injection

Koin is used for injecting:

API

Repository

ViewModels

🚀 How to Run the Project

Clone the repository or download the project

Open in Android Studio

Add your Watchmode API Key

Sync Gradle

Run on Emulator or Physical Device

🔑 API Key Setup

Replace your API key in:

viewModel.loadMoviesAndTv("YOUR_API_KEY")
