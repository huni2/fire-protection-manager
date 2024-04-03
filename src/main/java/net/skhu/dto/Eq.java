package net.skhu.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Eq {

	int EQ_SEQ;
	String EQ_NAME;
	String EQ_TYPE;
	Date EQ_INPUT_DATE;
	String EQ_USER;
	int EQ_STRESS_NUM;
	Date EQ_DISUSE;
	Date REG_DATE;
	String REG_USER;

	String UserId;
}