package net.mrtska.workbench;


/**[Module SlopeRecipeEntry.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/06/10
*/
public class SlopeRecipeEntry {

	//レシピパターン
	private boolean[] pattern = new boolean[9];

	private final String direction;


	public SlopeRecipeEntry(String direction, boolean[] pattern) {

		this.direction = direction;

		if(pattern.length != 9) {

			throw new RuntimeException("Illegal parameters.");
		}

		this.pattern = pattern;
	}

	public SlopeRecipeEntry(String direction, String recipe) {

		this.direction = direction;

		if(recipe.length() != 9) {

			throw new RuntimeException("Illegal parameters.");
		}

		for(int i = 0; i < pattern.length; i++) {

			char c = recipe.toCharArray()[i];
			if(c == '1') {

				pattern[i] = true;
			}
		}
	}


	//indexの位置にブロックを置くかどうか判定
	public boolean getPattern(int index) {

		return pattern[index];
	}

	//ブロックの向き、形状を取得
	public String getDirection() {

		return this.direction;
	}

	//パターンを出力
	@Override
	public String toString() {

		String ret = super.toString() + ":";

		for(boolean b : pattern) {

			if(b) {

				ret += "1";
			} else {

				ret += "0";
			}
		}

		return ret;
	}



}
