package com.uobia.gpes.model;

import java.util.ArrayList;
import java.util.List;

public class InfoFixture {

	public static List<Info> createInfo() {
		List<Info> infos = new ArrayList<Info>();
		infos.add(Info.create(1, 2, 3));
		infos.add(Info.create(1, 2, 4));
		infos.add(Info.create(1, 4, 5));
		infos.add(Info.create(3, 4, 5));
		infos.add(Info.create(6, 2, 5));
		return infos;
	}

}
