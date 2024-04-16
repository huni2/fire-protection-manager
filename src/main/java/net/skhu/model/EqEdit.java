package net.skhu.model;

import java.sql.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EqEdit {
	int EQ_SEQ;


    @NotEmpty @NotBlank
    @Size(min=8, max=12)
    String EQ_STRESS_NUM;

    @NotNull(message = "사용 중단 날짜는 필수입니다.")
    @Future(message = "사용 중단 날짜는 미래여야 합니다.")
    Date EQ_DISUSE;

    @NotNull(message = "등록 날짜는 필수입니다.")
    @PastOrPresent(message = "등록 날짜는 과거 혹은 현재여야 합니다.")
    Date REG_DATE;

    @NotNull(message = "입력 날짜는 필수입니다.")
    @PastOrPresent(message = "입력 날짜는 과거 혹은 현재여야 합니다.")
    Date EQ_INPUT_DATE;

    @NotEmpty @NotBlank
    @Size(min=2, max=20)
    String EQ_NAME;

    @NotEmpty @NotBlank
    @Size(min=2, max=20)
    String EQ_USER;

    @NotEmpty @NotBlank
    @Size(min=2, max=20)
    String EQ_TYPE;

    @NotEmpty @NotBlank
    @Size(min=2, max=20)
    String REG_USER;






//    @Min(value=1, message="학과를 선택하세요.")
//    int departmentId;

}
