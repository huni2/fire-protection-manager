package net.skhu.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FireSuitUsage {

	String id;

	String fireSuitId;

	String username;

    LocalDateTime usageDate;

    String purpose;

    int duration;

}
