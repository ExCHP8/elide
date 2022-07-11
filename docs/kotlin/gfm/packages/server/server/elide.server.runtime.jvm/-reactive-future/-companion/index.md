//[server](../../../../index.md)/[elide.server.runtime.jvm](../../index.md)/[ReactiveFuture](../index.md)/[Companion](index.md)

# Companion

[jvm]\
object [Companion](index.md)

## Functions

| Name | Summary |
|---|---|
| [cancelled](cancelled.md) | [jvm]<br>fun &lt;[R](cancelled.md)&gt; [cancelled](cancelled.md)(): [ReactiveFuture](../index.md)&lt;[R](cancelled.md)&gt;<br>Create an already-cancelled future. The future will present as both done and cancelled as soon as it is returned from this method. |
| [done](done.md) | [jvm]<br>fun &lt;[R](done.md)&gt; [done](done.md)(value: [R](done.md)): [ReactiveFuture](../index.md)&lt;[R](done.md)&gt;<br>Create an already-resolved future, wrapping the provided value. The future will present as done as soon as it is returned from this method. |
| [failed](failed.md) | [jvm]<br>fun &lt;[R](failed.md)&gt; [failed](failed.md)(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [ReactiveFuture](../index.md)&lt;[R](failed.md)&gt;<br>Create an already-failed future, wrapping the provided exception instance. The future will present as one as soon as it is returned from this method. |
| [wrap](wrap.md) | [jvm]<br>fun &lt;[R](wrap.md)&gt; [wrap](wrap.md)(apiFuture: ApiFuture&lt;[R](wrap.md)&gt;?): [ReactiveFuture](../index.md)&lt;[R](wrap.md)&gt;<br>fun &lt;[R](wrap.md)&gt; [wrap](wrap.md)(apiFuture: ApiFuture&lt;[R](wrap.md)&gt;?, executor: [Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html)?): [ReactiveFuture](../index.md)&lt;[R](wrap.md)&gt;<br>Wrap a Google APIs ApiFuture in a universal [ReactiveFuture](../index.md), such that it may be used with any interface requiring a supported async or future value.<br>[jvm]<br>fun &lt;[R](wrap.md)&gt; [wrap](wrap.md)(future: ListenableFuture&lt;[R](wrap.md)&gt;?): [ReactiveFuture](../index.md)&lt;[R](wrap.md)&gt;<br>fun &lt;[R](wrap.md)&gt; [wrap](wrap.md)(future: ListenableFuture&lt;[R](wrap.md)&gt;?, executor: [Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html)?): [ReactiveFuture](../index.md)&lt;[R](wrap.md)&gt;<br>Wrap a Guava ListenableFuture in a universal [ReactiveFuture](../index.md), such that it may be used with any interface requiring a supported async or future value.<br>[jvm]<br>fun &lt;[R](wrap.md)&gt; [wrap](wrap.md)(future: [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html)&lt;[R](wrap.md)&gt;?): [ReactiveFuture](../index.md)&lt;[R](wrap.md)&gt;<br>fun &lt;[R](wrap.md)&gt; [wrap](wrap.md)(future: [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html)&lt;[R](wrap.md)&gt;?, executor: [Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html)?): [ReactiveFuture](../index.md)&lt;[R](wrap.md)&gt;<br>Wrap a regular Java [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html) in a universal [ReactiveFuture](../index.md), such that it may be used with any interface requiring support for that class.<br>[jvm]<br>fun &lt;[R](wrap.md)&gt; [wrap](wrap.md)(publisher: Publisher&lt;[R](wrap.md)&gt;?): [ReactiveFuture](../index.md)&lt;[R](wrap.md)&gt;<br>Wrap a Reactive Java Publisher in a universal [ReactiveFuture](../index.md), such that it may be used with any interface requiring a supported async or future value. |