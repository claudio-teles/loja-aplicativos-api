package com.lojaaplicativosapi.util;

import java.util.UUID;

public class IdUtilSingleton {
	
	private static IdUtilSingleton instanciaIdUtil = new IdUtilSingleton();
	
	private UUID uuid;
	private UUID uuidWindows;
	private UUID uuidMac;
	private UUID uuidLinux;

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

	public UUID getUuidWindows() {
		return uuidWindows;
	}

	public void setUuidWindows(UUID uuidWindows) {
		this.uuidWindows = uuidWindows;
	}

	public UUID getUuidMac() {
		return uuidMac;
	}

	public void setUuidMac(UUID uuidMac) {
		this.uuidMac = uuidMac;
	}

	public UUID getUuidLinux() {
		return uuidLinux;
	}

	public void setUuidLinux(UUID uuidLinux) {
		this.uuidLinux = uuidLinux;
	}

}
