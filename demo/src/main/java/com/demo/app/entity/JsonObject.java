package com.demo.app.entity;

import org.springframework.util.Assert;

public class JsonObject<PK, E> {
	private PK id;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	private E cell;

	public E getCell() {
		return cell;
	}

	public void setCell(E cell) {
		this.cell = cell;
	}

	public JsonObject(PK pk, E e) {
		Assert.notNull(e, "格式化对象为空！");
		Assert.notNull(pk, "格式化对象主键为空！");
		cell = e;
		id = pk;
	}
}
