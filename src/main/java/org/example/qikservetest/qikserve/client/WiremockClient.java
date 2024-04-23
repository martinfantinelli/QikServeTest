package org.example.qikservetest.qikserve.client;

import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import com.google.gson.Gson;
import org.example.qikservetest.qikserve.domain.model.CartDetails;
import org.example.qikservetest.qikserve.domain.model.ItemRequest;
import org.example.qikservetest.qikserve.domain.model.Product;
import org.example.qikservetest.qikserve.domain.model.Promotion;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class WiremockClient {
    private static final String BASE_URL = "http://localhost:8080";
    private static final String URL_MOCK = "https://251k8.wiremockapi.cloud";
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

    public CartDetails fetchCartDetails() throws IOException {
        String url = URL_MOCK + "/details";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed in search for cart details: " + response);
        }

        String responseBody = response.body().string();
        CartDetails cartDetails = gson.fromJson(responseBody, CartDetails.class);

        return cartDetails;
    }

    public void addItem(String productId, int quantity) throws IOException {
        String url = URL_MOCK + "/addItem";

        String requestBodyJson = gson.toJson(new ItemRequest(productId, quantity));
        RequestBody requestBody = RequestBody.create(requestBodyJson, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Failed to add item to cart: " + response);
        }

        response.close();
    }
}

