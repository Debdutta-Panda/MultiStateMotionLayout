# MultiStateMotionLayout

We all know about motion layout in jetpack compose with only two state. I made a multi state motion layout. Enjoy it.

## YouTube
https://youtu.be/r6kgFRfTrjA

## Medium
https://medium.com/@debduttapanda/multi-state-motion-layout-in-jetpack-compose-b7291f7c0dc9

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
