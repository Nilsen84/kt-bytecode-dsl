## Kotlin Bytecode DSL
A DSL that assists with writing bytecode in ASM
> Note: This is NOT an abstraction layer, it simply reduces the amount of boilerplate you have to write

#### Usage
```kts
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.ow2.asm:asm-tree:9.4")
    implementation("com.github.Nilsen84:kt-bytecode-dsl:v1.0")
}
```

#### Extensibility
This library does not come with any utility functions, though these can easily be added using extensions:
```kotlin
fun InsnBuilder.int(n: Int) = when (n) {
    in -1..5 -> +InsnNode(Opcodes.ICONST_0 + n)
    in Byte.MIN_VALUE..Byte.MAX_VALUE -> bipush(n)
    in Short.MIN_VALUE..Short.MAX_VALUE -> sipush(n)
    else -> ldc(n)
}
```

```kotlin
val InsnBuilder.iconst_6 get() = bipush(6)
```

#### Comparisons
<table>
<tr>
<th>Java</th>
<th>Kotlin</th>
<th>Kotlin + Bytecode DSL</th>
</tr>
<tr>
<td>

```java
var list = new InsnList() {{
    add(new FieldInsnNode(
        Opcodes.GETSTATIC,
        "java/lang/System",
        "out",
        "Ljava/io/PrintStream;"
    ));
    add(new LdcInsnNode("Hello, World!"));
    add(new MethodInsnNode(
        Opcodes.INVOKEVIRTUAL,
        "java/io/PrintStream",
        "println",
        "(Ljava/lang/String;)V"
    ));
}};
```

</td>

<td>

```kotlin
val list = InsnList().apply {
    add(FieldInsnNode(
        Opcodes.GETSTATIC,
        "java/lang/System",
        "out",
        "Ljava/io/PrintStream;"
    ))
    add(LdcInsnNode("Hello, World!"))
    add(MethodInsnNode(
        Opcodes.INVOKEVIRTUAL,
        "java/io/PrintStream",
        "println",
        "(Ljava/lang/String;)V"
    ))
}
```

</td>

<td>

```kotlin
val list = asm {
    getstatic(
        "java/lang/System",
        "out",
        "Ljava/io/PrintStream;"
    )
    ldc("Hello, World!")
    invokevirtual(
        "java/io/PrintStream",
        "println",
        "(Ljava/lang/String;)V"
    )
}



```

</td>
</table>
