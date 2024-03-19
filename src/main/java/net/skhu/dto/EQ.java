package net.skhu.dto;

//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//	
//@Getter
//@Setter
//@ToString
//public class EQ {
//	int EQ_SEQ;
//	String EQ_NAME;
//	String EQ_TYPE;
//	int EQ_INPUT_DATE;
//	String EQ_USER;
//	int EQ_STRESS_NUM;
//	int EQ_DISUSE;
//	int REG_DATE;
//	String REG_USER;
//	}

import lombok.Data;

@Data
public class EQ {
	
	private int EQ_SEQ;
	
	private String EQ_NAME;
	
	private String EQ_TYPE;
	
	private int EQ_INPUT_DATE;
	
	private String EQ_USER;
	
	private String EQ_STRESS_NUM;
	
	private String EQ_DISUSE;
	
	private String REG_DATE;
	
	private String REG_USER;
}
