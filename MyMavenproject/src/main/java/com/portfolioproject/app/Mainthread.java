package com.portfolioproject.app;

import com.portfolioproject.concurrent.PriceUpdateTask;
import com.portfolioproject.model.Stock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Mainthread {

    public static void main(String[] args) {
        Stock apple = new Stock("AAPL", "Apple", 180.0, 185.0);
        Stock google = new Stock("GOOG", "Google", 140.0, 145.0);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            Future<Double> applePrice = executor.submit(new PriceUpdateTask(apple));
            Future<Double> googlePrice = executor.submit(new PriceUpdateTask(google));
            System.out.println("Apple updated price: " + applePrice.get());
            System.out.println("Google updated price: " + googlePrice.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Price updates were interrupted.");
        } catch (ExecutionException e) {
            System.err.println("A price update failed: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}