package com.jayms.treasurehunt.util;

import com.jayms.treasurehunt.Entity;

public class DebugHelper {

	public DebugHelper() {
	}
	
	public void dumpInConsole(Entity entity) {
		System.out.println("Dumping Entity: ");
		System.out.println("\tUUID: " + entity.getUniqueID().toString());
		System.out.println("\tLoc: " + entity.getLocation().toString());
		System.out.println("\tTexture: " + entity.getTexture().getImage().impl_getUrl());
	}
}
