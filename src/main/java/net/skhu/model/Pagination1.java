package net.skhu.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import lombok.Data;

@Data
public class Pagination1 {
    int pg = 1;        // 현재 페이지 번호
    int sz = 15;       // 페이지 당 레코드 수
    int recordCount;   // 전체 레코드 수
    int od = 0;        // 정렬 순서
    String eq = "";    // 검색 문자열

    public int getFirstRecordIndex() {
        return (pg - 1) * sz;
    }

    public String getQueryString() {
        try {
            String encoded = URLEncoder.encode(eq, "UTF-8");
            return String.format("pg=%d&sz=%d&od=%d&st=%s", pg, sz, od, encoded);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("pg=%d&sz=%d&od=%d&st=%s", pg, sz, od, eq);
    }
}