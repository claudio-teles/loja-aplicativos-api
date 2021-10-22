package com.lojaaplicativosapi.util;

import java.util.UUID;

public class IdUtilSingleton {
	
	private static IdUtilSingleton instanciaIdUtil = new IdUtilSingleton();
	
	private UUID uuid;

	public IdUtilSingleton() {}
	
	public static IdUtilSingleton getInstancia() {
		if (instanciaIdUtil == null) {
			IdUtilSingleton.instanciaIdUtil = new IdUtilSingleton();
		}
		return IdUtilSingleton.instanciaIdUtil;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
