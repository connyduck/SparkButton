[![Maven Central](https://maven-badges.herokuapp.com/maven-central/at.connyduck.sparkbutton/sparkbutton-compose/badge.svg)](https://maven-badges.herokuapp.com/maven-central/at.connyduck.sparkbutton/sparkbutton-compose)

# SparkButton Compose

Jetpack Compose library to create buttons with sparkly animation

## Dependency
Add the repository in your root build.gradle.kts if you haven't already:

```kotlin
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```
Then add the dependency:

```kotlin
dependencies {
    ...
    implementation("at.connyduck.sparkbutton:sparkbutton-compose:<version>")
}
```

## Usage
```kotlin
var checked by remember { mutableStateOf(false) }

SparkButton(
    animateOnClick = !checked,
    onClick = {
        checked = !checked
    },
    modifier = Modifier.size(32.dp)
) {
    if (checked) {
        Icon(
            painter = painterResource(R.drawable.ic_heart_filled),
            contentDescription = stringResource(R.string.unlike)
        )
    } else {
        Icon(
            painter = painterResource(R.drawable.ic_heart),
            contentDescription = stringResource(R.string.like)
        )
    }
}
```

see [ComposeDemo.kt](../app/src/main/java/at/connyduck/sparkbutton/sample/ComposeDemo.kt) for more examples.

### Customization

The `SparkButton` Composable function has a few parameters to customize the spark animation:

- `contentSize`: To adjust the size of the animation.
- `primaryColor`/`secondaryColor`: Use these to customize the color of the sparks. For best results they should be colors that go well together, e.g. yellow and orange, or light and dark blue.
- `animationSpeed`: Set to a number between 0 and 1 to slow the animation down or to over 1 to speed it up. Defaults to 1 which equals a 1 second animation.

## License
Licensed under [Apache 2.0](../LICENSE.md)


