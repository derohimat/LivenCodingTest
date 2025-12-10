# LivenCodingTest

[English Version below](#en)

Project ini adalah aplikasi Android yang dikembangkan sebagai bagian dari Liven Coding Test. Aplikasi ini menerapkan prinsip modern Android development dengan arsitektur **Clean Architecture** dan pola **MVVM (Model-View-ViewModel)**.

## 🏗 Struktur & Arsitektur

Aplikasi ini dibangun dengan memisahkan kode ke dalam lapisan-lapisan (layers) yang berbeda untuk memastikan *separation of concerns*, testability, dan maintainability.

### Pola Arsitektur: Clean Architecture + MVVM

1.  **UI Layer (Presentation)**
    *   Berisi komponen antarmuka pengguna (UI) yang dibangun menggunakan **Jetpack Compose**.
    *   **ViewModel** digunakan untuk mengelola state UI dan menjembatani komunikasi antara UI dan Domain layer.
    *   Lokasi: `ui/` (contoh: `ui/books/`)

2.  **Domain Layer**
    *   Merupakan lapisan bisnis inti yang tidak bergantung pada framework Android.
    *   Berisi **Model** (data class) dan **Repository Interfaces**.
    *   Lokasi: `domain/`

3.  **Data Layer**
    *   Bertanggung jawab untuk mengambil data dari sumber eksternal (API) atau lokal (Database).
    *   Mengimplementasikan interface repository dari Domain layer (`BooksRepositoryImpl`).
    *   Menggunakan **Retrofit** untuk network calls dan **Room** untuk penyimpanan lokal.
    *   Lokasi: `data/`

4.  **DI (Dependency Injection)**
    *   Menggunakan **Hilt** untuk mengelola dependensi aplikasi secara otomatis.
    *   Lokasi: `di/`

---

## 🛠 Teknologi & Library (Tech Stack)

Project ini menggunakan library dan tools modern, dikelola melalui **Version Catalog** (`libs.versions.toml`):

*   **Bahasa**: [Kotlin](https://kotlinlang.org/)
*   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetbrains/compose) (Material3)
*   **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
*   **Asynchronous Processing**: [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) & Flow
*   **Network**: [Retrofit](https://squareup.com/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
*   **Local Storage**: [Room Database](https://developer.android.com/training/data-storage/room)
*   **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
*   **Navigation**: [Compose Navigation](https://developer.android.com/guide/navigation/navigation-compose)
*   **Serialization**: Kotlin Serialization & Gson
*   **Build System**: Gradle (Kotlin DSL)

---

## 📂 Struktur Folder

Berikut adalah gambaran umum struktur kode dalam project (`app/src/main/java/com/derohimat/livencodingtest/`):

```text
├── common/             # Utilities dan helper classes (contoh: Resource state)
├── data/               # Implementasi Data Layer
│   ├── local/          # Room Database, DAO, Entities
│   ├── remote/         # Retrofit API interfaces, DTOs
│   └── repository/     # Implementasi Repository (menghubungkan local & remote)
├── di/                 # Modul Dependency Injection (Hilt Modules)
├── domain/             # Domain Layer (Business Logic)
│   ├── model/          # Model data murni (bebas dari framework)
│   └── repository/     # Interface Repository
└── ui/                 # Presentation Layer
    ├── books/          # Screen dan ViewModel untuk fitur Books
    └── theme/          # Konfigurasi tema aplikasi (Color, Type, Theme)
```

## 🚀 Cara Menjalankan Project

1.  **Clone repository** ini ke komputer Anda.
2.  Buka project menggunakan **Android Studio** (Reptile/terbaru).
3.  Pastikan koneksi internet aktif untuk mengunduh dependency gradle.
4.  Lakukan **Sync Project with Gradle Files**.
5.  Pilih device emulator atau fisik, lalu klik **Run** (`Shift + F10`).

## 🧪 Testing

Project ini telah dikonfigurasi untuk mendukung testing:
*   **Unit Test**: Menggunakan JUnit 4 (`src/test`).
*   **Instrumentation Test**: Menggunakan Espresso dan Compose UI Test (`src/androidTest`).

<br>
<br>

---
<a name="en"></a>

# LivenCodingTest (English Version)

This project is an Android application developed as part of the Liven Coding Test. It applies modern Android development principles with **Clean Architecture** and the **MVVM (Model-View-ViewModel)** pattern.

## 🏗 Structure & Architecture

The application is built by separating code into distinct layers to ensure *separation of concerns*, testability, and maintainability.

### Architectural Pattern: Clean Architecture + MVVM

1.  **UI Layer (Presentation)**
    *   Contains user interface (UI) components built using **Jetpack Compose**.
    *   **ViewModel** is used to manage UI state and bridge communication between the UI and Domain layers.
    *   Location: `ui/` (example: `ui/books/`)

2.  **Domain Layer**
    *   The core business layer that is independent of the Android framework.
    *   Contains **Models** (data classes) and **Repository Interfaces**.
    *   Location: `domain/`

3.  **Data Layer**
    *   Responsible for fetching data from external sources (API) or local sources (Database).
    *   Implements repository interfaces from the Domain layer (`BooksRepositoryImpl`).
    *   Uses **Retrofit** for network calls and **Room** for local storage.
    *   Location: `data/`

4.  **DI (Dependency Injection)**
    *   Uses **Hilt** to manage application dependencies automatically.
    *   Location: `di/`

---

## 🛠 Technology & Libraries (Tech Stack)

This project uses modern libraries and tools, managed via **Version Catalog** (`libs.versions.toml`):

*   **Language**: [Kotlin](https://kotlinlang.org/)
*   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetbrains/compose) (Material3)
*   **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
*   **Asynchronous Processing**: [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) & Flow
*   **Network**: [Retrofit](https://squareup.com/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
*   **Local Storage**: [Room Database](https://developer.android.com/training/data-storage/room)
*   **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
*   **Navigation**: [Compose Navigation](https://developer.android.com/guide/navigation/navigation-compose)
*   **Serialization**: Kotlin Serialization & Gson
*   **Build System**: Gradle (Kotlin DSL)

---

## 📂 Folder Structure

Here is an overview of the code structure in the project (`app/src/main/java/com/derohimat/livencodingtest/`):

```text
├── common/             # Utilities and helper classes (e.g., Resource state)
├── data/               # Data Layer Implementation
│   ├── local/          # Room Database, DAO, Entities
│   ├── remote/         # Retrofit API interfaces, DTOs
│   └── repository/     # Repository Implementation (bridges local & remote)
├── di/                 # Dependency Injection Modules (Hilt Modules)
├── domain/             # Domain Layer (Business Logic)
│   ├── model/          # Pure data models (framework-free)
│   └── repository/     # Repository Interfaces
└── ui/                 # Presentation Layer
    ├── books/          # Screen and ViewModel for Books feature
    └── theme/          # Application theme configuration (Color, Type, Theme)
```

## 🚀 How to Run the Project

1.  **Clone this repository** to your computer.
2.  Open the project using **Android Studio** (Reptile/latest).
3.  Ensure you have an active internet connection to download gradle dependencies.
4.  Perform **Sync Project with Gradle Files**.
5.  Select an emulator or physical device, then click **Run** (`Shift + F10`).

## 🧪 Testing

The project is configured to support testing:
*   **Unit Tests**: Using JUnit 4 (`src/test`).
*   **Instrumentation Tests**: Using Espresso and Compose UI Test (`src/androidTest`).
