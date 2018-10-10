package com.ienai.dto;

import java.util.List;

/**
 * 
 * <p>Title: NoteLikeDataReceiver</p>
 * <p>Description: 用于接受NoteMetaRecord的实体类</p>
 * @author akira
 * @date 2018年10月8日 上午11:32:46
 */
public class NoteLikeDataReceiver {

	private List<NoteMetaRecord> records;

	public List<NoteMetaRecord> getRecords() {
		return records;
	}

	public void setRecords(List<NoteMetaRecord> records) {
		this.records = records;
	}
}
