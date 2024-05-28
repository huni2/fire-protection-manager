package net.skhu.model;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FireSuitUsageEdit {
    String id;

    @NotNull(message = "사용자 이름은 필수입니다.")
    String username;

    @NotNull(message = "사용 날짜는 필수입니다.")
    Date usageDate;

    @NotNull(message = "사용 날짜는 필수입니다.")
    Date fireSuitId;

    @NotNull(message = "사용 목적은 필수입니다.")
    String purpose;

    @NotNull(message = "사용 시간은 필수입니다. (분 단위로)")
    Integer duration; // 분 단위


}


