package com.sgz.banlv;

import com.sgz.banlv.controller.AgentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

@SpringBootTest
class BanlvApplicationTests {

    @Autowired
    AgentController agentController;

    @Test
    void contextLoads() {
        String json = "{\n" +
                "    \"agent_id\": 123,\n" +
                "    \"city_id\": 456,\n" +
                "    \"scenicZone_name\": \"Scenic Zone 1\",\n" +
                "    \"scenicspotDto\": [\n" +
                "        {\n" +
                "            \"scenicSpot_name\": \"Scenicspot 1\",\n" +
                "            \"scenicSpot_longitude\": 1.234,\n" +
                "            \"scenicSpot_latitude\": 2.345,\n" +
                "            \"scenicSpot_remark\": \"Remark 1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"scenicSpot_name\": \"Scenicspot 2\",\n" +
                "            \"scenicSpot_longitude\": 3.456,\n" +
                "            \"scenicSpot_latitude\": 4.567,\n" +
                "            \"scenicSpot_remark\": \"Remark 2\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
//        agentController.SaveCoordinateRecord()
    }

    @Test
    void test() {
        ArrayList arrayList = new ArrayList();

        if(!arrayList.isEmpty()){
            System.out.println("reee");
        }else{
            System.out.println("pppppp");
        }
    }

    @Test
    void test4() {
        long second = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond();
        System.out.println(second);
        // 将时间戳转为当前时间
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, ZoneOffset.ofHours(8));
        // 2020-02-03T13:30:44
        System.out.println(localDateTime);
    }
}
