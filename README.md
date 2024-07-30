# Weather App

This Weather App demonstrates the use of Clean Architecture and the MVI (Model-View-Intent) architectural pattern in Android development. I made this project to understand the MVI architectural pattern by following a tutorial on YouTube by Philipp Lackner 
<a href = "https://youtu.be/eAbKK7JNxCE?si=fujh5k3CKw_ZHJ9m">Tutorial link</a>.



## Table of Contents
- [Screenshot](#screenshot)
- [Introduction](#introduction)
- [Features](#features)
- [Architecture](#architecture)
- [Setup](#setup)

## Screenshot
<image width = 216px height = 480px src="images/img.png"/>

## Introduction
The Weather App provides current weather information for any location. It is built using the MVI architectural pattern, which ensures a unidirectional data flow, making state management predictable and easy to debug. This app also follows Clean Architecture principles, promoting separation of concerns and testability.

## Features
- Fetch current weather data for a specific location.
- Display weather information including temperature, humidity, and weather conditions.
- Handle loading and error states gracefully.

## Architecture
The app follows Clean Architecture principles, divided into three layers:
1. **Presentation Layer**: Contains UI components and handles user interactions.
2. **Domain Layer**: Contains business logic and use cases. In this project, I haven't used use cases because it is a simple app, using a domain layer will be overhead.
3. **Data Layer**: Manages data from various sources (e.g., network).

### MVI Components:
- **Model**: Manages the state of the application.
- **View**: Renders the UI based on the state.
- **Intent**: Represents user actions and system events.

## Setup
Follow these steps to set up and run the project:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/regxl2/MVI-Weather-App.git
