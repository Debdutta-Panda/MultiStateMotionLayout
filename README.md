# MultiStateMotionLayout

We all know about motion layout in jetpack compose with only two state. I made a multi state motion layout. Enjoy it.

![image](https://user-images.githubusercontent.com/92369023/202374192-e421c32b-32f1-4787-96be-d1c614b99570.png)

# Demo

![0_Ah6VEcfnbPN3c7bI](https://user-images.githubusercontent.com/92369023/202374587-02ed8360-baa6-4074-bad9-41347fe458ea.gif)



## YouTube
https://youtu.be/r6kgFRfTrjA

## Medium
https://medium.com/@debduttapanda/multi-state-motion-layout-in-jetpack-compose-b7291f7c0dc9

## Reddit

https://www.reddit.com/r/androiddev/comments/yxj0h0/multistate_motion_layout_in_jetpack_compose/

## Usage

```kotlin
MultiStateMotionLayout(
    modifier: Modifier = Modifier,
    stateSet: StateSet,
    stateConstraints: Map<Any,ConstraintSet>,
    transition: Transition? = null,
    debug: EnumSet<MotionLayoutDebugFlags> = EnumSet.of(MotionLayoutDebugFlags.NONE),
    crossinline content: @Composable MotionLayoutScope.() -> Unit
)
```

```kotlin
data class StateSet(
    val startState: Any,
    val endState: Any,
    val progress: Progress
)
```
Use anything as state identifier, integer, enum, string whatever you want.

### Progress

There can be various way to mention progress

- Hard coded value
    e.g. `StateSet.Value(0.3f)`
- State
    e.g. `StateSet.State(your_state_of_float)`
- Animation
    e.g. `StateSet.Animation`

### Animation

```kotlin
Animation(
    val from: Float = 0f,
    val to: Float = 1f,
    val duration: Int = 500,
    val animationSpec: AnimationSpec<Float> = tween(duration),
    val key: Any,
    val onFinishedListener: (()->Unit)? = null
)
```

# Sample

https://github.com/Debdutta-Panda/MultiStateMotionLayout/blob/main/app/src/main/java/com/debduttapanda/multistatemotionlayout/MainActivity.kt

# License

https://github.com/Debdutta-Panda/MultiStateMotionLayout/blob/main/LICENSE.md
