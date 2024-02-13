## how restapis handles multiple request simaltanously

REST APIs are built on top of the HTTP protocol, which is a stateless protocol. In a RESTful service, each HTTP request
that the server receives is treated independently of others. In a multi-user environment where there are potentially 
multiple simultaneous requests, the server handles each request in a separate thread.

When the HTTP server receives a request, it routes that request to the relevant endpoint and spins off a new thread to
handle the request processing. This is how simultaneous processing of multiple requests is facilitated. The server 
doesn't wait for one request to finish processing before starting on the next one.

This means that if there are two simultaneous requests to a REST API, the server can process them concurrently, in separate threads, without making one request wait for the other to finish.

It's important, however, to note that this depends on the server's configuration, such as the maximum number of threads that it can handle simultaneously. Also, it's crucial to manage resources effectively and avoid computational or memory-intensive operations in API endpoints to prevent any performance bottlenecks.

So, REST APIs, inherently backed by multithreaded web servers, are capable of handling multiple requests simultaneously.

## 
While REST APIs are capable of processing multiple requests concurrently due to being backed by multithreaded web servers, 
this does not necessarily mean they are asynchronous in nature.

In a typical REST API setup, the processing is synchronous, meaning the client sends a request and then waits for the server
to process that request and send a response before it continues with its execution.

However, the server can handle multiple such synchronous requests at the same time by processing each in a separate
thread or process, giving the illusion of simultaneous execution or asynchronicity from a user's point of view.

The server's ability to process multiple requests at once stems from the multi-threading capability of the web server and not from the inherent asynchronicity of the REST API. In other words, multiple clients can interact with the server concurrently, but the interaction between a specific client and server is synchronous.

Nevertheless, it's possible to design your REST APIs to be asynchronous by using techniques such as futures, callbacks, or support provided by languages and frameworks like Java's CompletableFuture, JavaScript's Promises, or Spring's @Async annotation. Such APIs, instead of making the client wait for the response, will immediately return a future or a callback, and the actual response is then sent later when the processing is complete.


# There are multiple ways to handle Asynchronous Rest APIs in Java. 
The two major ones are: using Spring's `@Async` annotation and
using `DeferredResult` in Spring MVC. Let's explore both of them.

1. **Using Spring's `@Async` Annotation:**

   You can see how to use `@Async` in the examples given in previous responses.
   Overall, you enable async processing by using `@EnableAsync` and 
   configure your Executor service using `@Bean`. Then in your controller classes when you want a method to run asynchronously,
   use `@Async` annotation on that method.

2. **Using `DeferredResult` in Spring MVC:**

   This is another way to handle asynchronous processing in REST APIs. Instead of returning the actual object, your methods will return `DeferredResult<T>`. The actual processing will be done in some background thread. When it's done, 
   it will call `deferredResult.setResult(result)`; and that will return the output to the client.


In above example, we are using `CompletableFuture` for processing. The result of `CompletableFuture` is then set to `DeferredResult` in `whenCompleteAsync()` method.

These are the main ways to handle Asynchronous processing. As mentioned before, make sure that Asynchronous processing is appropriate for your use case, as it involves its own complexities like error handling, managing resources, etc.