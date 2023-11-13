package com.example.github.githubaction.ListUtilTest;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListUtilTest {
    @Test
    void TestSumThing() {
        /*
        String compCd = map.getString("comp_cd");// 회사코드
        String wmsRqstSeq = map.getString("wms_rqst_seq");// 차수
        String wmsRqstDt = map.getString("wms_rqst_dt");// 일자
        3개를 가지고 있는 맵 목록 생성
        */

        Set<String> filterList = new HashSet<>();

        List<Map<String, String>> list = List.of(
                Map.of("wms_rqst_seq", "1", "wms_rqst_dt", "20231201"),
                Map.of("wms_rqst_seq", "1", "wms_rqst_dt", "20231201"),
                Map.of("wms_rqst_seq", "2", "wms_rqst_dt", "20231201")
        );

        for (Map<String, String> map : list) {
            String wmsRqstSeq = map.get("wms_rqst_seq");// 차수
            String wmsRqstDt = map.get("wms_rqst_dt");// 차수
            String dumpText = wmsRqstSeq + "_" + wmsRqstDt;

            filterList.add(dumpText);
        }

        for (String str : filterList) {
            String[] arrs = str.split("_");
        }
    }


}
