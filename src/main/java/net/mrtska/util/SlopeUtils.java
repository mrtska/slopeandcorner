package net.mrtska.util;

import net.mrtska.block.SlopeTab;

/**[Module SlopeUtils.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/27
*/
public class SlopeUtils {


	public static SlopeTab cornerTab;
	public static SlopeTab slopeTab;
	public static SlopeTab edgecornerTab;
	public static SlopeTab halfslopeTab;



	//重なる斜面ブロックの対応した向きを返す
	public static String convertDirection(String direction) {

		switch(direction) {

		case "NORTH":
			return "RSOUTH";
		case "EAST":
			return "RWEST";
		case "SOUTH":
			return "RNORTH";
		case "WEST":
			return "REAST";
		case "RNORTH":
			return "SOUTH";
		case "REAST":
			return "WEST";
		case "RSOUTH":
			return "NORTH";
		case "RWEST":
			return "EAST";
		case "NORTH2":
			return "SOUTH2";
		case "EAST2":
			return "WEST2";
		case "SOUTH2":
			return "NORTH2";
		case "WEST2":
			return "EAST2";
		}

		return "NORTH";
	}

}
