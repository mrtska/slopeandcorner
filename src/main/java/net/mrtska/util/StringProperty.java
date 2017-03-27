package net.mrtska.util;

import net.minecraftforge.common.property.IUnlistedProperty;

/**[Module TextureProperty.java]
 Copyright(c) 2015 mrtska.starring
 This software is released under the MIT License.
 http://opensource.org/licenses/mit-license.php
 Created on: 2015/04/19
*/
public class StringProperty implements IUnlistedProperty<String> {

	private final String name;

	public StringProperty(String name) {

		this.name = name;
	}

	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public boolean isValid(String value) {

		return value instanceof String;
	}

	@Override
	public Class<String> getType() {

		return String.class;
	}

	@Override
	public String valueToString(String value) {

		return value;
	}

}
