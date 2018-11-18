Switching from an imperative and synchronous programming paradigm to a reactive and asynchronous one can sometimes be 
daunting. One of the steepest steps in the learning curve is how to analyze and debug when something goes wrong.

In the imperative world, debugging is usually pretty straightforward: just read the stacktrace and you see where the 
problem originated and more: Was it entirely a failure of your code? Did the failure occur in some library code? If so, 
what part of your code called the library, potentially passing in improper parameters that ultimately caused the failure?

### 1. The Typical Reactor Stack Trace
When you shift to asynchronous code, things can get much more complicated.
Consider the following stack trace:
```markdown
java.lang.IndexOutOfBoundsException: Source emitted more than one item
	at reactor.core.publisher.MonoSingle$SingleSubscriber.onNext(MonoSingle.java:120)
	at reactor.core.publisher.FluxFlatMap$FlatMapMain.emitScalar(FluxFlatMap.java:380)
	at reactor.core.publisher.FluxFlatMap$FlatMapMain.onNext(FluxFlatMap.java:349)
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:119)
	at reactor.core.publisher.FluxRange$RangeSubscription.slowPath(FluxRange.java:144)
	at reactor.core.publisher.FluxRange$RangeSubscription.request(FluxRange.java:99)
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.request(FluxMapFuseable.java:172)
	at reactor.core.publisher.FluxFlatMap$FlatMapMain.onSubscribe(FluxFlatMap.java:316)
	at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onSubscribe(FluxMapFuseable.java:94)
	at reactor.core.publisher.FluxRange.subscribe(FluxRange.java:68)
	at reactor.core.publisher.FluxMapFuseable.subscribe(FluxMapFuseable.java:67)
	at reactor.core.publisher.FluxFlatMap.subscribe(FluxFlatMap.java:98)
	at reactor.core.publisher.MonoSingle.subscribe(MonoSingle.java:58)
	at reactor.core.publisher.Mono.subscribeWith(Mono.java:2668)
	at reactor.core.publisher.Mono.subscribe(Mono.java:2629)
	at reactor.core.publisher.Mono.subscribe(Mono.java:2604)
	at reactor.core.publisher.Mono.subscribe(Mono.java:2582)
	at reactor.guide.GuideTests.debuggingCommonStacktrace(GuideTests.java:722)
```

There is a lot going on there. We get an IndexOutOfBoundsException, which tells us that a "source emitted more than one item".

We can probably quickly come to assume that this source is a Flux/Mono, as confirmed by the line below that mentions 
MonoSingle. So it appears to be some sort of complaint from a single operator.

Referring to the Javadoc for Mono#single operator, we see that single has a contract: The source must emit exactly one 
element. It appears we had a source that emitted more than one and thus violated that contract.

Can we dig deeper and identify that source? The following rows are not very helpful. They take us on a travel inside the
internals of what seems to be a reactive chain, through multiple calls to subscribe and request.

By skimming over these rows, we can at least start to form a picture of the kind of chain that went wrong: It seems to 
involve a MonoSingle, a FluxFlatMap, and a FluxRange (each gets several rows in the trace, but overall these three 
classes are involved). So a range().flatMap().single() chain maybe?

But what if we use that pattern a lot in our application? This still does not tell us much, and simply searching for 
single isn’t going to find the problem. Then the last line refers to some of our code. Finally, we are getting close.

Hold on, though. When we go to the source file, all we see is that a pre-existing Flux is subscribed to, as follows:
```
toDebug.subscribe(System.out::println, Throwable::printStackTrace);
```
All of this happened at subscription time, but the Flux itself was not declared there. Worse, when we go to where the 
variable is declared, we see:
```
public Mono<String> toDebug; //please overlook the public class attribute
```

The variable is not instantiated where it is declared. We must assume a worst-case scenario where we find out that there
could be a few different code paths that set it in the application. We remain unsure of which one caused the issue.

What we want to find out more easily is where the operator was added into the chain - that is, where the Flux was 
declared. We usually refer to that as the assembly of the Flux.

### 2. Activating Debug Mode
Fortunately, Reactor comes with a debugging-oriented capability of assembly-time instrumentation.
This is done by customizing the Hook.onOperator hook at application start (or at least before the incriminated Flux or 
Mono can be instantiated), as follows:
```
Hooks.onOperatorDebug();
```
This starts instrumenting the calls to the Flux (and Mono) operator methods (where they are assembled into the chain) by
wrapping the construction of the operator and capturing a stacktrace there. Since this is done when the operator chain 
is declared, the hook should be activated before that, so the safest way is to activate it right at the start of your application.

Later on, if an exception occurs, the failing operator is able to refer to that capture and append it to the stack trace.

### 3. Reading a Stack Trace in Debug Mode
When we reuse our initial example but activate the operatorStacktrace debug feature, the stack trace is as follows:
```markdown
java.lang.IndexOutOfBoundsException: Source emitted more than one item
	at reactor.core.publisher.MonoSingle$SingleSubscriber.onNext(MonoSingle.java:120)
	at reactor.core.publisher.FluxOnAssembly$OnAssemblySubscriber.onNext(FluxOnAssembly.java:314) 
...

...
	at reactor.core.publisher.Mono.subscribeWith(Mono.java:2668)
	at reactor.core.publisher.Mono.subscribe(Mono.java:2629)
	at reactor.core.publisher.Mono.subscribe(Mono.java:2604)
	at reactor.core.publisher.Mono.subscribe(Mono.java:2582)
	at reactor.guide.GuideTests.debuggingActivated(GuideTests.java:727)
	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Assembly trace from producer [reactor.core.publisher.MonoSingle] : 
	reactor.core.publisher.Flux.single(Flux.java:5335)
	reactor.guide.GuideTests.scatterAndGather(GuideTests.java:689)
	reactor.guide.GuideTests.populateDebug(GuideTests.java:702)
	org.junit.rules.TestWatcher$1.evaluate(TestWatcher.java:55)
	org.junit.rules.RunRules.evaluate(RunRules.java:20)
Error has been observed by the following operator(s): 
	|_	Flux.single(TestWatcher.java:55) 
```
As you can see, the captured stack trace is appended to the original error as a suppressed OnAssemblyException. 
There are two parts to it, but the first section is the most interesting. It shows the path of construction for the 
operator that triggered the exception. Here it shows that the single that caused our issue was created in the 
scatterAndGather method, itself called from a populateDebug method that got executed through JUnit.

Now that we are armed with enough information to find the culprit, we can have a meaningful look at that scatterAndGather method:
```
private Mono<String> scatterAndGather(Flux<String> urls) {
    return urls.flatMap(url -> doRequest(url))
           .single(); 
}
```
Now we can see what the root cause of the error was a flatMap that performs several HTTP calls to a few URLs is chained
with single, which is too restrictive. After a short git blame and a quick discussion with the author of that line, we 
find out he meant to use the less restrictive take(1) instead.

That second part of the debug stack trace was not necessarily interesting in this particular example, because the error
was actually happening in the last operator in the chain (the one closest to subscribe). Considering another example 
might make it more clear:
```
FakeRepository.findAllUserByName(Flux.just("pedro", "simon", "stephane"))
              .transform(FakeUtils1.applyFilters)
              .transform(FakeUtils2.enrichUser)
              .blockLast();
```
Now imagine that, inside findAllUserByName, there is a map that fails. Here we would see the following final traceback:
```
Error has been observed by the following operator(s):
        |_        Flux.map(FakeRepository.java:27)
        |_        Flux.map(FakeRepository.java:28)
        |_        Flux.filter(FakeUtils1.java:29)
        |_        Flux.transform(GuideDebuggingExtraTests.java:41)
        |_        Flux.elapsed(FakeUtils2.java:30)
        |_        Flux.transform(GuideDebuggingExtraTests.java:42)
```

This corresponds to the section of the chain of operators that gets notified of the error:
1.The exception originates in the first map.
2.It is seen by a second map (both in fact correspond to the findAllUserByName method).
3.It is then seen by a filter and a transform, which indicate that part of the chain is constructed via a reusable 
transformation function (here, the applyFilters utility method).
4.Finally, it is seen by an elapsed and a transform. Once again, elapsed is applied by the transformation function of 
that second transform.

We deal with a form of instrumentation here, and creating a stack trace is costly. That is why this debugging feature 
should only be activated in a controlled manner, as a last resort.

### 4. Logging a Stream
In addition to stack trace debugging and analysis, another powerful tool to have in your toolkit is the ability to trace
and log events in an asynchronous sequence.

The log() operator can do just that. Chained inside a sequence, it will peek at every event of the Flux or Mono upstream
of it (including onNext, onError, and onComplete and subscriptions, cancellations, and requests).

The log operator uses the Loggers utility class, which picks up common logging frameworks like Log4J and Logback through
SLF4J and defaults to logging to the console in case SLF4J is unavailable.

The Console fallback uses System.err for the WARN and ERROR log levels and System.out for everything else.

If you prefer a JDK java.util.logging fallback, as in 3.0.x, you can get it by setting the reactor.logging.fallback 
System property to JDK.

























