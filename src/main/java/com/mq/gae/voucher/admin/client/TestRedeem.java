package com.mq.gae.voucher.admin.client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/5/2015
 */
public class TestRedeem {
    public static void main(String[] args) throws IOException, InterruptedException {
        String voucherCodesPath = "D:\\codes.txt";
        int threadCount = 10;
        String serverAddress = "http://localhost:8080";
        //String serverAddress = "https://voucher-admin.appspot.com";

        List<String> strings = Files.readAllLines(Paths.get(voucherCodesPath), Charset.defaultCharset());

        final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>(strings);

        List<Thread> threads = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(new Worker(queue, serverAddress)));
        }

        for (int i = 0; i < threadCount; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads.get(i).join();
        }

    }

    private static class Worker implements Runnable {
        private ConcurrentLinkedQueue<String> queue;
        private String serverAddress;

        public Worker(ConcurrentLinkedQueue<String> queue, String serverAddress) {
            this.queue = queue;
            this.serverAddress = serverAddress;
        }

        @Override
        public void run() {
            String s;
            while ((s = queue.poll()) != null) {
                try {
                    HttpClient client = HttpClientBuilder.create().build();
                    HttpPut put = new HttpPut(serverAddress + "/_ah/api/voucheradmin/v2/communities/1/vouchers/" + s + "/redeem");
                    put.addHeader("Content-Type", "application/json");
                    put.setEntity(new StringEntity("{\"id\":33333,\"userName\":\"" + Thread.currentThread().getName() + "\"}"));

                    org.apache.http.HttpResponse response = client.execute(put);

                    BufferedReader rd = new BufferedReader(
                            new InputStreamReader(response.getEntity().getContent()));

                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }

                    System.out.println(Thread.currentThread().getName() + " : " + result.toString().replaceAll("\n", ""));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
