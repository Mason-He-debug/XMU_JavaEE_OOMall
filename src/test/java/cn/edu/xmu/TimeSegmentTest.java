package cn.edu.xmu;

import cn.edu.xmu.oomall.DemoApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Date;

@SpringBootTest(classes = DemoApplication.class)
class TimeSegmentTest {

    private WebTestClient webTestClient;

    public TimeSegmentTest(){
        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }



    /*
     * 200：成功
     */

    @Test
    public void testCreateAdvertisementTimeSegment0() throws JSONException {

        String requestBody = "{\n" +
                "\"beginTime\":\"2020-12-01 00:12:03\",\n" +
                "\"endTime\":\"2020-12-09 07:15:00\"\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"OK\",\n" +
                "  \"errmsg\": \"成功\",\n" +
                "  \"data\": {\n" +
                "    \"beginTime\": \"2020-12-01 00:12:03\",\n" +
                "    \"endTime\": \"2020-12-09 07:15:00\"\n" +
                "  }\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/advertisement/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, false);

    }


    /*
     * 610: 开始时间大于结束时间
     */

    @Test
    public void testCreateAdvertisementTimeSegment610() throws JSONException {
        String requestBody = "{\n" +
                "\"beginTime\":\"2020-12-20 00:12:03\",\n" +
                "\"endTime\":\"2020-12-09 07:15:00\"\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"610\",\n" +
                "  \"errmsg\": \"开始时间大于结束时间\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/advertisement/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType("application/json")
                .expectBody()
                .returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, true);

    }



    /*
     * 611：开始时间不能为空
     */
    public void testCreateAdvertisementTimeSegment611() throws JSONException {
        String requestBody = "{\n" +
                "\"beginTime\":null,\n" +
                "\"endTime\":\"2020-12-20 00:12:03\"\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"611\",\n" +
                "  \"errmsg\": \"开始时间不能为空\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/advertisement/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType("application/json")
                .expectBody()
                .returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, true);

    }


    /*
     * 612：结束时间不能为空
     */
    public void testCreateAdvertisementTimeSegment612() throws JSONException {

        String requestBody = "{\n" +
                "\"beginTime\":\"2020-12-20 00:12:03\",\n" +
                "\"endTime\":null\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"612\",\n" +
                "  \"errmsg\": \"结束时间不能为空\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/advertisement/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType("application/json")
                .expectBody()
                .returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, true);

    }





    @Test
    public void testGetAdvertisementTimeSegment0(){



    }







    /*
     * 200
     */
    @Test
    public void testDeleteAdvertisementTimeSegment0() throws JSONException {

        String expectedRespondeBody = "{\n" +
                "  \"errno\": 0,\n" +
                "  \"errmsg\": \"成功\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.delete().uri("/advertisement/timesegments/2")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedRespondeBody, receivedResponseBody, true);

    }



    /*
     * 504：操作的资源id不存在
     */
    @Test
    public void testDeleteAdvertisementTimeSegment504a() throws JSONException {

        String expectedRespondeBody = "{\n" +
                "  \"errno\": 504,\n" +
                "  \"errmsg\": \"操作的资源id不存在\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.delete().uri("/advertisement/timesegments/2333")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedRespondeBody, receivedResponseBody, true);

    }


    /*
     * 504：操作的资源id不存在
     * 此时段 id 对应的是秒杀时段，而非广告时段
     */
    @Test
    public void testDeleteAdvertisementTimeSegment504b() throws JSONException {

        String expectedRespondeBody = "{\n" +
                "  \"errno\": 504,\n" +
                "  \"errmsg\": \"操作的资源id不存在\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.delete().uri("/advertisement/timesegments/10")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedRespondeBody, receivedResponseBody, true);

    }



    /*
     * 200：成功
     */

    @Test
    public void testCreateFlashSaleTimeSegment0() throws JSONException {

        String requestBody = "{\n" +
                "\"beginTime\":\"2020-12-01 00:12:03\",\n" +
                "\"endTime\":\"2020-12-09 07:15:00\"\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"OK\",\n" +
                "  \"errmsg\": \"成功\",\n" +
                "  \"data\": {\n" +
                "    \"beginTime\": \"2020-12-01 00:12:03\",\n" +
                "    \"endTime\": \"2020-12-09 07:15:00\"\n" +
                "  }\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/flashsale/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, false);

    }


    /*
     * 610: 开始时间大于结束时间
     */

    @Test
    public void testCreateFlashSaleTimeSegment610() throws JSONException {

        String requestBody = "{\n" +
                "\"beginTime\":\"2020-12-20 00:12:03\",\n" +
                "\"endTime\":\"2020-12-09 07:15:00\"\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"610\",\n" +
                "  \"errmsg\": \"开始时间大于结束时间\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/flashsale/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType("application/json")
                .expectBody()
                .returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, true);

    }



    /*
     * 611：开始时间不能为空
     */
    public void testCreateFlashSaleTimeSegment611() throws JSONException {

        String requestBody = "{\n" +
                "\"beginTime\":null,\n" +
                "\"endTime\":\"2020-12-20 00:12:03\"\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"611\",\n" +
                "  \"errmsg\": \"开始时间不能为空\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/flashsale/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, true);

    }


    /*
     * 612：结束时间不能为空
     */
    public void testCreateFlashSaleTimeSegment612() throws JSONException {

        String requestBody = "{\n" +
                "\"beginTime\":\"2020-12-20 00:12:03\",\n" +
                "\"endTime\":null\n" +
                "}";

        String expectedResponseBody = "{\n" +
                "  \"errno\": \"612\",\n" +
                "  \"errmsg\": \"结束时间不能为空\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.post().uri("http://localhost:8080/flashsale/timesegments")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedResponseBody, receivedResponseBody, true);

    }






    /*
     * 200
     */
    @Test
    public void testDeleteFlashSaleTimeSegment0() throws JSONException {

        String expectedRespondeBody = "{\n" +
                "  \"errno\": 0,\n" +
                "  \"errmsg\": \"成功\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.delete().uri("/flashsale/timesegments/11")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedRespondeBody, receivedResponseBody, true);

    }



    /*
     * 504：操作的资源id不存在
     * 不存在任何类型的时间段符合此 id
     */
    @Test
    public void testDeleteFlashSaleTimeSegment504a() throws JSONException {

        String expectedRespondeBody = "{\n" +
                "  \"errno\": 504,\n" +
                "  \"errmsg\": \"操作的资源id不存在\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.delete().uri("/flashsale/timesegments/2333")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedRespondeBody, receivedResponseBody, true);

    }


    /*
     * 504：操作的资源id不存在
     * 此 id 对应的时段属于广告时段而不属于秒杀时段
     */
    @Test
    public void testDeleteFlashSaleTimeSegment504b() throws JSONException {

        String expectedRespondeBody = "{\n" +
                "  \"errno\": 504,\n" +
                "  \"errmsg\": \"操作的资源id不存在\"\n" +
                "}";

        byte[] receivedResponseBodyData = webTestClient.delete().uri("/flashsale/timesegments/5")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody().returnResult().getResponseBodyContent();

        String receivedResponseBody = new String(receivedResponseBodyData);

        JSONAssert.assertEquals(expectedRespondeBody, receivedResponseBody, true);

    }








}
