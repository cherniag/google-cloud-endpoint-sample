package com.mq.gae.voucher.admin.client;

import com.google.api.client.http.*;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.mq.gae.voucher.admin.api.UserDto;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/5/2015
 */
public class TestRedeem {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> strings = Files.readAllLines(Paths.get("D:\\codes.txt"), Charset.defaultCharset());

        final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>(strings);

        Thread t1 = new Thread(new Worker(queue));
        Thread t2 = new Thread(new Worker(queue));
        Thread t3 = new Thread(new Worker(queue));
        Thread t4 = new Thread(new Worker(queue));
        Thread t5 = new Thread(new Worker(queue));
        Thread t6 = new Thread(new Worker(queue));
        Thread t7 = new Thread(new Worker(queue));
        Thread t8 = new Thread(new Worker(queue));
        Thread t9 = new Thread(new Worker(queue));
        Thread t10 = new Thread(new Worker(queue));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        t1.join();
        t10.join();
    }

    private static class Worker implements Runnable {

        private ConcurrentLinkedQueue<String> queue;

        public Worker(ConcurrentLinkedQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String s;
            while((s = queue.poll())!=null) {
                try {
                    String url = "https://voucher-admin.appspot.com/_ah/api/voucheradmin/v2/communities/1/vouchers/" + s + "/redeem";

                    HttpClient client = HttpClientBuilder.create().build();
                    HttpPut put = new HttpPut(url);
                    put.addHeader("Content-Type", "application/json");
                    put.setEntity(new StringEntity("{\"id\":33333,\"userName\":\""+ Thread.currentThread().getName() + "\"}"));

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
