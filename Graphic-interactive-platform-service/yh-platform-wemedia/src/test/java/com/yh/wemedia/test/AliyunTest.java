package com.yh.wemedia.test;

import com.yh.common.aliyun.GreenImageScan;
import com.yh.common.aliyun.GreenTextScan;
import com.yh.file.service.FileStorageService;
import com.yh.wemedia.WemediaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = WemediaApplication.class)
@RunWith(SpringRunner.class)
public class AliyunTest {

    @Autowired
    private GreenTextScan greenTextScan;

    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void testScanText() throws Exception {
        greenTextScan.greeTextScan("我是一个好人");

        //没钱买审核暂代
        Map<String, String> map = new HashMap<>();
        map.put("suggestion","pass");

        System.out.println(map);
    }

    @Test
    public void testScanImage() throws Exception {
        byte[] bytes = fileStorageService.downLoadFile("http://192.168.88.130:9090/yhbucket/2023/08/02/5ba56e7240dc4fd8b144998783bc8bde.jpg,http://192.168.88.130:9090/yhbucket/2023/08/02/fa2143d81e0140feb942c8cd647d1e46.jpg,http://192.168.88.130:9090/yhbucket/2023/08/02/4bd036b1a5b14fd7b186ac3e78e59cb3.jpg");
        greenImageScan.imageScan(Arrays.asList(bytes));
        //没钱买审核暂代
        Map<String, String> map = new HashMap<>();
        map.put("suggestion","pass");

        System.out.println(map);
    }
}