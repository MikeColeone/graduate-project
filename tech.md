- 为什么不使用原来的application.properties,而是再次创建yml文件
  - 原因是前者是逐行的配置 后者是嵌套式配置更好理解 简洁易于维护和管理 更加简洁
- Raw use of parameterized class 'ArrayList' 
  - 添加泛型作为限制提高安全性
  - ```java
            ArrayList<ArrayList<Object>>list =new ArrayList<>();
```
- Call to 'printStackTrace()' should probably be replaced with more robust loggin
  - 换成 log.error("exception message", e); 

- ctrl+alt+l快速整理代码


## 异步非阻塞流式api
在 Reactor 库中（主要用于响应式编程），`Flux` 和 `Mono` 是核心的发布者（Publisher）类型。以下是对您提到的各个方法的详细解释：

---

### **Flux 与 Mono 的基本概念：**

- **Mono**：表示 0 或 1 个元素的异步序列（类似于 Java 的 `Optional`）。
- **Flux**：表示 0 到 N 个元素的异步序列（类似于 Java 的 `Stream`）。

---

### **常用方法解释：**

### **1. `map()`**
将发布者中的每个元素映射为另一个元素。
```java
Flux<Integer> flux = Flux.just(1, 2, 3)
  .map(x -> x * 2); // 输出：2, 4, 6
```

### **2. `flatMap()`**
将每个元素映射为一个新的 `Publisher`，然后将它们合并。
```java
Flux<String> flux = Flux.just("A", "B")
  .flatMap(s -> Flux.just(s + "1", s + "2"));
// 输出：A1, A2, B1, B2
```

### **3. `filter()`**
过滤元素，只保留满足条件的元素。
```java
Flux<Integer> flux = Flux.range(1, 5)
  .filter(x -> x % 2 == 0); // 输出：2, 4
```

### **4. `handle()`**
自定义处理每个元素，可以条件性地发出元素或终止序列。
```java
Flux<Integer> flux = Flux.range(1, 5)
  .handle((value, sink) -> {
    if (value % 2 == 0) sink.next(value * 10);
  }); // 输出：20, 40
```

### **5. `mapNotNull()`**
与 `map()` 类似，但会过滤掉 `null` 值（Spring WebFlux 附加方法）。
```java
Flux<String> flux = Flux.just("A", null, "B")
  .mapNotNull(value -> value); // 输出：A, B
```

### **6. `switchIfEmpty()`**
如果发布者没有发出任何元素，则切换到另一个发布者。
```java
Mono<String> mono = Mono.empty()
  .switchIfEmpty(Mono.just("Default Value")); // 输出：Default Value
```

### **7. `defaultIfEmpty()`**
如果发布者为空，则发出一个默认值。
```java
Mono<String> mono = Mono.empty()
  .defaultIfEmpty("Default"); // 输出：Default
```

### **8. `Mono.defer()`**
延迟创建 Mono，在订阅时才执行创建逻辑。
```java
Mono<String> mono = Mono.defer(() -> Mono.just("Hello"));
```

### **9. `onErrorResume()`**
捕获错误并返回一个新的发布者。
```java
Mono<String> mono = Mono.error(new RuntimeException("Error"))
  .onErrorResume(e -> Mono.just("Recovered")); // 输出：Recovered
```

### **10. `Mono.error()`**
创建一个立即发出错误信号的 `Mono`。
```java
Mono<String> mono = Mono.error(new RuntimeException("Error"));
```

### **11. `Flux.merge()`**
合并多个 `Flux` 流。
```java
Flux<String> flux = Flux.merge(
  Flux.just("A", "B"),
  Flux.just("1", "2")
); // 输出：A, B, 1, 2（可能无序）
```

### **12. `Mono.when()`**
等待多个 `Mono` 完成，并发出 `Void`。
```java
Mono<Void> result = Mono.when(Mono.just(1), Mono.just(2));
```

### **13. `Mono.just()` 和 `Flux.just()`**
创建一个包含指定元素的 `Mono` 或 `Flux`。
```java
Mono<String> mono = Mono.just("Hello");
Flux<String> flux = Flux.just("A", "B", "C");
```

### **14. `then()`**
忽略当前流的元素，只在完成时发出信号。
```java
Mono<Void> mono = Flux.just(1, 2, 3).then(); // 无输出，仅完成信号
```

### **15. `thenReturn()`**
在流完成后返回一个指定值。
```java
Mono<String> mono = Flux.just(1, 2, 3).thenReturn("Done"); // 输出：Done
```

### **16. `Flux.from()`**
从其他发布者创建一个 `Flux`。
```java
Flux<Integer> flux = Flux.from(Flux.just(1, 2, 3));
```

### **17. `Flux.fromIterable()`**
从一个 `Iterable`（如 `List`）创建 `Flux`。
```java
List<String> list = Arrays.asList("A", "B", "C");
Flux<String> flux = Flux.fromIterable(list);
```

### **18. `cast()`**
将流中的元素强制转换为指定类型。
```java
Flux<Number> flux = Flux.just(1, 2, 3).cast(Number.class);
```

---

### **总结：**
这些方法提供了丰富的功能来处理响应式流，包括数据转换、错误处理、流控制等。理解和熟练使用它们有助于构建强大的响应式应用程序。