# 🎬 Practical 6 — Frame-by-Frame Animation & Splash Screen

**Aim:** Create an Android application to demonstrate Frame-by-Frame Animation, Splash Screen, and Twin/Tween Animation.

## 🎯 Objective

This practical implements animation techniques in an Android application using Kotlin and XML drawable resources.

The project demonstrates:

- MainActivity and SplashActivity
- AnimationDrawable
- Alarm-clock frame animation
- Heart frame animation
- Splash-screen frame animation
- Twin/Tween animation resource
- AnimationUtils
- Animation listener
- Edge-to-Edge display
- Window-focus-based animation control

The project contains separate animation resources and image/vector assets under `res/drawable`.

---

## 📂 Project Structure

```text
24012021096_MAD_Pr6/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/
│           │       └── a2401201180_mad_practical_6/
│           │           ├── MainActivity.kt
│           │           └── SplashActivity.kt
│           │
│           ├── res/
│           └── AndroidManifest.xml
│
├── gradle/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

*Note: The displayed structure is illustrative. Resource filenames and package names should match the actual repository.*

---

## 🎞️ What Is Frame-by-Frame Animation?

Frame-by-frame animation displays a sequence of drawable images one after another to create the appearance of movement.

In this project, Android's `AnimationDrawable` class is used for frame animation.

An XML `animation-list` defines the sequence and duration of each frame.

## ⏰ Alarm Frame Animation

The repository contains ten alarm images:

```text
alarm1.jpg
alarm2.jpg
alarm3.jpg
alarm4.jpg
alarm5.jpg
alarm6.jpg
alarm7.jpg
alarm8.jpg
alarm9.jpg
alarm10.jpg
```

These images are referenced by:

```text
alarm_animation_list.xml
```

The animation list is configured with:

```xml
android:oneshot="false"
```

Each alarm frame has a duration of **200 milliseconds**. The animation repeats instead of running only once.

### Animation Sequence

```text
alarm1 → alarm2 → alarm3 → alarm4 → alarm5
   ↑                                      ↓
alarm10 ← alarm9 ← alarm8 ← alarm7 ← alarm6
```

---

## ❤️ Heart Frame Animation

The project also contains a heart animation using Android vector drawable resources.

### Heart Animation Frames

```text
ic_heart_0.xml
ic_heart_25.xml
ic_heart_50.xml
ic_heart_75.xml
ic_heart_100.xml
```

These resources are referenced by:

```text
heart_animation_list.xml
```

Each frame uses a duration of **200 milliseconds**.

The animation list uses:

```xml
android:oneshot="false"
```

Therefore, the heart animation repeats continuously.

### Animation Sequence

```text
0% → 25% → 50% → 75% → 100%
 ↑                              ↓
 └──────── Repeats ─────────────┘
```

---

## 🧩 MainActivity

`MainActivity.kt` initializes the alarm and heart frame animations.

### Alarm Animation

The alarm `ImageView` is assigned the animation-list resource:

```kotlin
alarm.setBackgroundResource(
    R.drawable.alarm_animation_list
)

alarmanimation =
    alarm.background as AnimationDrawable
```

### Heart Animation

The same approach is used for the heart:

```kotlin
heart.setBackgroundResource(
    R.drawable.heart_animation_list
)

heartanimation =
    heart.background as AnimationDrawable
```

The resulting `AnimationDrawable` objects control the alarm and heart animations.

---

## 👁️ Animation and Window Focus

The project overrides the following method:

```kotlin
override fun onWindowFocusChanged(hasFocus: Boolean)
```

### When the Activity Has Focus

```kotlin
alarmanimation.start()
heartanimation.start()
```

Both frame animations start when the Activity has window focus.

### When the Activity Loses Focus

```kotlin
alarmanimation.stop()
heartanimation.stop()
```

Both animations stop when the Activity loses window focus.

This approach controls frame animation according to the Activity's window-focus state.

---

## 🌈 Splash Screen

The project contains a separate `SplashActivity.kt` responsible for displaying the application's splash screen.

The SplashActivity:

1. Loads `activity_splash.xml`.
2. Finds the logo `ImageView`.
3. Assigns `uvpce_animation_list` as its background.
4. Converts the background into an `AnimationDrawable`.
5. Loads the twin/tween animation using `AnimationUtils`.
6. Registers itself as an `AnimationListener`.

---

## 🎞️ Splash Frame Animation

The splash logo is assigned its animation-list resource:

```kotlin
imglogo.setBackgroundResource(
    R.drawable.uvpce_animation_list
)

guniframeanim =
    imglogo.background as AnimationDrawable
```

This allows the splash-screen logo to be animated frame by frame.

---

## ✨ Twin/Tween Animation

Twin/tween animation applies transformations to a view over a specified duration. Android supports transformations such as scaling, translation, rotation, and alpha changes.

The splash screen loads its animation resource using:

```kotlin
gunianim = AnimationUtils.loadAnimation(
    this,
    R.anim.twin_animation
)
```

The animation listener is attached using:

```kotlin
gunianim.setAnimationListener(this)
```

This provides an additional animation effect on the splash-screen logo.

### Required XML Animation Tags

The animation resource can use the following XML tags and attributes:

| Tag / Attribute | Purpose |
|---|---|
| `<set>` | Groups multiple animations |
| `<scale>` | Changes the size of a view |
| `<translate>` | Moves a view |
| `<rotate>` | Rotates a view |
| `<alpha>` | Changes opacity |
| `android:startOffset="100"` | Delays the start of an animation by 100 ms |
| `android:duration="1000"` | Sets the animation duration to 1000 ms |
| `<animation-list>` | Defines a sequence of drawable frames |
| `android:oneshot="false"` | Repeats the frame animation |

---

## 🔄 Splash Animation Flow

```text
          Application Start
                  |
                  v
           SplashActivity
                  |
                  v
           Splash Layout
                  |
                  v
        UVPCE Frame Animation
                  |
                  +
                  |
         Twin/Tween Animation
                  |
                  v
           Animation Ends
                  |
                  v
            MainActivity
```

When the twin/tween animation finishes, `SplashActivity` starts `MainActivity`.

---

## ⏯️ Starting and Stopping Splash Animation

The `onWindowFocusChanged()` method controls the splash frame animation.

### When Focus Is Available

```kotlin
guniframeanim.start()
imglogo.startAnimation(gunianim)
```

### When Focus Is Lost

```kotlin
guniframeanim.stop()
```

This makes the frame animation dependent on the Activity's window-focus state.

---

## 🔔 Animation Listener

`SplashActivity` implements:

```kotlin
Animation.AnimationListener
```

The `onAnimationEnd()` callback handles navigation to the main screen:

```kotlin
override fun onAnimationEnd(animation: Animation?) {
    Intent(this, MainActivity::class.java)
        .also { startActivity(it) }
}
```

When the tween animation finishes, the application opens `MainActivity`.

---

## 🖼️ Image and Drawable Resources

The animation-related resources include:

### Alarm Frames

```text
alarm1.jpg
alarm2.jpg
...
alarm10.jpg
```

### Animation Lists

```text
alarm_animation_list.xml
heart_animation_list.xml
uvpce_animation_list.xml
```

### Heart Vector Drawables

```text
ic_heart_0.xml
ic_heart_25.xml
ic_heart_50.xml
ic_heart_75.xml
ic_heart_100.xml
```

The alarm frames are raster images, while the heart frames are Android vector drawable XML files.

---

## 📚 Concepts Covered

| Concept | Implementation |
|---|---|
| ImageView | Displays animation content |
| AnimationDrawable | Frame-by-frame animation |
| `animation-list` | Defines animation frames |
| `onWindowFocusChanged()` | Controls animation based on focus |
| AnimationUtils | Loads animation resources |
| `loadAnimation()` | Loads the tween animation |
| AnimationListener | Detects animation events |
| `onAnimationEnd()` | Opens MainActivity after the tween animation |
| Splash Screen | Implemented using SplashActivity |
| Frame Animation | Alarm, heart, and logo sequences |
| Twin/Tween Animation | Splash-screen logo animation |
| Edge-to-Edge | Enabled in both Activities |
| Vector Drawable | Heart animation frames |

---

## 🛠️ Development Steps

1. Create the Android project.
2. Create `MainActivity`.
3. Create `SplashActivity`.
4. Design the main and splash layouts.
5. Add the alarm frame images.
6. Create `alarm_animation_list.xml`.
7. Add the heart vector drawable frames.
8. Create `heart_animation_list.xml`.
9. Assign the animation lists to their respective `ImageView` elements.
10. Convert the drawable backgrounds into `AnimationDrawable` objects.
11. Start and stop animations using `onWindowFocusChanged()`.
12. Create the splash logo animation list.
13. Create the twin/tween animation resource.
14. Load the animation using `AnimationUtils`.
15. Implement an `AnimationListener`.
16. Start `MainActivity` after the splash animation ends.
17. Run the application and verify both animations.

---

## ▶️ How to Run

1. Open the project in Android Studio.
2. Allow Gradle synchronization to complete.
3. Run the application on an Android emulator or physical Android device.
4. Observe the animated splash screen.
5. Wait for the splash animation to finish.
6. Observe the main Activity.
7. Verify the animated alarm-clock image.
8. Verify the heart animation.
9. Move the application out of focus and return to it to observe the animation start/stop behavior.

---

## ✅ Result

The Android application demonstrates **Frame-by-Frame Animation, Splash Screen, and Twin/Tween Animation** using Kotlin, `AnimationDrawable`, XML animation resources, `AnimationUtils`, and `AnimationListener`.

The project also demonstrates edge-to-edge display and window-focus-based animation control.

## 📱 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/7b0f9819-e9fd-4b7e-8660-85190f9f97e1" width="250" alt="Splash Screen" />
  &nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/d8b35fca-4ce8-4a65-b01a-197dbc1d3e72" width="250" alt="Main Activity" />
</p>
