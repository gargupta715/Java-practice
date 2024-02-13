package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
// REST APIs, by default, operate synchronously
@RestController
public class AsyncController {
    @GetMapping("/asyncDeferred")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult() {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();

        // Do processing in separate thread
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello from DeferredResult!";
        }).whenCompleteAsync((result, throwable) -> deferredResult.setResult(ResponseEntity.ok(result)));

        return deferredResult;
    }


    @Async("taskExecutor")
    @GetMapping("/async-endpoint")
    public CompletableFuture<String> asyncEndpoint() {
        return CompletableFuture.supplyAsync(() -> {
            // emulate long process
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello from Async Endpoint!";
        });
    }
}
