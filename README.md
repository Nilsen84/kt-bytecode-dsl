### Bytecode Kotlin DSL
A DSL that assists with writing bytecode in ASM
> Note: This is NOT an abstraction layer, it simply reduces the amount of boilerplate you have to write.

#### Examples
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