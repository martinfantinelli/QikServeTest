package org.example.qikservetest.qikserve.client;

import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import com.google.gson.Gson;
import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.domain.model.Promotion;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class WiremockClient {
    private static final String BASE_URL = "http://localhost:8080";
    private final OkHttpClient client;
    private final Gson gson;

    public WiremockClient() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public List<Product> fetchProducts() throws IOException {
        String url = BASE_URL + "/products";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed in search for products: " + response);
        }

        Type productListType = new TypeToken<List<Product>>() {}.getType();
        List<Product> productList = gson.fromJson(response.body().string(), productListType);

        return productList;
    }

    public Product fetchProductById(String productId) throws IOException {
        String url = BASE_URL + "/products/" + productId;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed in search for product with ID " + productId + ": " + response);
        }

        return gson.fromJson(response.body().string(), Product.class);
    }

    public Promotion fetchPromotionByProductId(String productId) throws IOException {
        String url = BASE_URL + "/products/" + productId;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed in search for promotion with ID " + productId + ": " + response);
        }

        return gson.fromJson(response.body().string(), Promotion.class);
    }
}

