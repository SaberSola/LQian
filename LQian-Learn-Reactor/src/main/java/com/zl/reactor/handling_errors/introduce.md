In Reactive Streams, errors are terminal events. As soon as an error occurs, it stops the sequence and gets propagated 
down the chain of operators to the last step, the Subscriber you defined and its onError method.

Such errors should still be dealt with at the application level. For instance, you might display an error notification 
in a UI or send a meaningful error payload in a REST endpoint. For this reason, the subscriber’s onError method should 
always be defined.
(这种错误仍然应该在应用程序级别处理。例如，您可以在UI中显示错误通知，或者在REST端点中发送有意义的错误有效负载。因此，订阅者的onError方法
应该始终被定义)

If not defined, onError throws an UnsupportedOperationException. You can further detect and triage it with the 
Exceptions.isErrorCallbackNotImplemented method.

Reactor also offers alternative means of dealing with errors in the middle of the chain, as error-handling operators.

Before you learn about error-handling operators, you must keep in mind that any error in a reactive sequence is a 
terminal event. Even if an error-handling operator is used, it does not allow the original sequence to continue. 
Rather, it converts the onError signal into the start of a new sequence. In other words, it replaces the terminated 
sequence upstream.
(在了解错误处理操作符之前，必须记住，反应序列中的任何错误都是终端事件。即使使用了错误处理操作符，也不允许原始序列继续。相反，它将onError信号
转换为新序列的开始。换句话说，它替换了上游终止的序列)

### Error Handling Operators
You may be familiar with several ways of dealing with exceptions in a try-catch block. Most notably, these include 
the following:
1. Catch and return a static default value.
2. Catch and execute an alternative path with a fallback method.
3. Catch and dynamically compute a fallback value.
4. Catch, wrap to a BusinessException, and re-throw.
5. Catch, log an error-specific message, and re-throw.
6. Use the finally block to clean up resources or a Java 7 "try-with-resource" construct.

All of these have equivalents in Reactor, in the form of error-handling operators.
