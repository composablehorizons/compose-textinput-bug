# Jetpack Compose TextInput Bug

This repo reproduces a bug/behavior using Jetpack Compose while typing.

If there is any delay between typing and updating the text of a `TextField` then the text can be messed up if the text input is too fast.

A workaround for this is create a stateful version of `TextField` which keeps the text it needs to display, and notify the caller for updates to the text.

## Video demonstration

https://user-images.githubusercontent.com/1665273/208224437-011e138e-f15a-4751-9558-ecd4872f0857.mp4

For full implementation see [MainActivity.kt](https://github.com/Composables-co/compose-textinput-bug/blob/main/app/src/main/java/co/composables/textinputbug/MainActivity.kt)
