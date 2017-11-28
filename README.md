# ClearableEditText  

## Gradle
- In project level  
```gradle
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```
- In app level  
```gradle
dependencies {
    ...
    compile 'com.github.htdangkhoa:ClearableEditText:0.1.0'
}
```

## Usage  
Using in XML
```xml
<com.github.htdangkhoa.library.ClearableEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:cet_src="@drawable/ic_clear"
        app:cet_visible_mode="editing" />
```
