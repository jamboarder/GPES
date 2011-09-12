package com.uobia.gpes.model;

import java.util.ArrayList;
import java.util.List;

public class InfoFixture {

	public static List<Info> createInfo() {
		List<Info> infos = new ArrayList<Info>();
		infos.add(Info.create(1, 2, 3));
		infos.add(Info.create(3, 5, 6));
		infos.add(Info.create(7, 8, 9));
		return infos;
	}

}
