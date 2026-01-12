# MovieDiscover

# 🎬 Movie & TV Show Discovery App  
**Android | Jetpack Compose | Watchmode API**

---

## 📌 Project Overview

The **Movie & TV Show Discovery App** is an Android application built using **Jetpack Compose** that allows users to browse movies and TV shows fetched from the **Watchmode API**.

The app follows modern Android development best practices including:
- **MVVM Architecture**
- **Retrofit for networking**
- **RxJava (Single.zip) for parallel API calls**
- **Koin for Dependency Injection**
- **Shimmer loading effects** for better UX

---

## 🎯 Objective

The goal of this project is to build a clean and scalable Android application that:

- Fetches and displays Movies and TV Shows from an external API  
- Uses MVVM architecture for separation of concerns  
- Performs multiple API calls simultaneously using **RxJava Single.zip**  
- Handles loading and error states gracefully  

---

## ✨ Features

### 🏠 Home Screen
- Fetches **Movies** and **TV Shows** from the Watchmode API  
- Uses **Single.zip** to load both datasets simultaneously  
- Tab-based toggle to switch between Movies and TV Shows  
- Displays:
  - Poster image  
  - Title  
  - Release year  
- Shows a **shimmer effect** while data is loading  

---

### 📄 Details Screen
- Navigates to a detailed view when an item is clicked  
- Displays:
  - Title  
  - Description  
  - Release year  
  - Poster image  
- Shows a **shimmer effect** while details are loading  

---

### ⚠️ Error Handling
- Network and API errors are handled gracefully  
- Errors are shown using **Snackbars**  
- Retry option is provided  

---

## 🛠 Tech Stack

| Technology | Purpose |
|----------|---------|
| Kotlin | Programming language |
| Jetpack Compose | Modern UI toolkit |
| Retrofit | REST API networking |
| RxJava / RxKotlin | Asynchronous operations |
| Single.zip | Parallel API calls |
| Koin | Dependency Injection |
| Coil | Image loading |
| Watchmode API | Movies & TV shows data |


## 🧱 Architecture (MVVM)

UI (Jetpack Compose)
↓
ViewModel
↓
Repository
↓
Retrofit API (Watchmode)




### Layers Explanation
- **UI Layer**: Jetpack Compose screens (Home & Details)
- **ViewModel Layer**: Manages UI state and business logic
- **Repository Layer**: Handles data fetching and API calls
- **Data Layer**: API interfaces and data models

---

## 🔗 API Integration

- Two API calls are executed **simultaneously** using `Single.zip`:
  - Fetch Movies
  - Fetch TV Shows

```kotlin
Single.zip(
    api.getMovies(apiKey),
    api.getTvShows(apiKey)
)


## ⏳ Loading State Handling

- **Shimmer effects** are displayed to indicate loading states:
  - On the **Home Screen** while movie and TV show lists are being fetched
  - On the **Details Screen** while item details are loading

This approach improves perceived performance and enhances the overall user experience.

---

## 📦 Dependency Injection

- **Koin** is used for Dependency Injection to manage:
  - Retrofit API service
  - Repository layer
  - ViewModels

Using Koin ensures a **clean architecture**, reduces boilerplate code, and improves scalability and testability.

---

## 🚀 How to Run the Project

1. Clone or download the project repository  
2. Open the project in **Android Studio**  
3. Add your **Watchmode API Key**  
4. Sync Gradle files  
5. Run the app on an **Android Emulator** or **Physical Device**

---

## 🔑 API Key Setup

Pass your Watchmode API key while calling the API from the ViewModel:

```kotlin
viewModel.loadMoviesAndTv("YOUR_API_KEY")


